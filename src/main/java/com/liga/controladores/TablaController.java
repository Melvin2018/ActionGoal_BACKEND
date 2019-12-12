package com.liga.controladores;

import com.liga.controladores.inteligencia.MethodsTable;
import com.liga.entidades.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.liga.entidades.Tabla;
import com.liga.entidades.Temporada;
import com.liga.repositorios.IPartido;
import com.liga.repositorios.ITabla;
import com.liga.repositorios.ITemporada;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tabla")
public class TablaController {

    @Autowired
    private IPartido par;
    @Autowired
    private ITabla tabla;
    @Autowired
    private ITemporada tempo;

    public Temporada ultima() {
        return tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
    }

    @GetMapping(value = "/All")
    public List<Tabla> listado() {
        Temporada te = this.ultima();
        return te.getTablaList().stream().sorted((x, y) -> y.getPuntos().compareTo(x.getPuntos())).collect(Collectors.toList());
    }

    @GetMapping(value = "/ingreso/{ID}")
    public boolean Registro(@PathVariable("ID") Integer id) {
        Partido p = par.getOne(id);
        Temporada te = this.ultima();
        if (te.getEstado() == 1)
        {
            te.setEstado(2);
            tempo.save(te);
        } else if (p.getJornada().getNumero() == te.getJornadaList().size())
        {
            if (p.getJornada().getPartidoList().stream().noneMatch(x -> x.getFecha() == null))
            {
                te.setEstado(3);
                tempo.save(te);
            }
        }
        Tabla t = te.getTablaList().stream()
                .filter(x -> Objects.equals(x.getEquipo().getId(), p.getEquipo1().getId()))
                .collect(Collectors.toList()).get(0);
        tabla.save(new MethodsTable().tabla(t, p.getGolList()));
        Tabla t1 = te.getTablaList().stream()
                .filter(x -> Objects.equals(x.getEquipo().getId(), p.getEquipo2().getId()))
                .collect(Collectors.toList()).get(0);
        tabla.save(new MethodsTable().tabla(t1, p.getGolList()));
        return true;
    }
}
