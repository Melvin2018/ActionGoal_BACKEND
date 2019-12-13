package com.liga.controladores;

import com.liga.controladores.inteligencia.MethodsTable;
import com.liga.entidades.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.liga.entidades.Tabla;
import com.liga.entidades.Temporada;
import com.liga.entidades.extras.Resultado;
import com.liga.repositorios.IPartido;
import com.liga.repositorios.ITabla;
import com.liga.repositorios.ITemporada;
import java.util.Comparator;
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
        if (te.getTablaList().isEmpty())
        {
            te.getEquipoTemporadaList().forEach(x ->
            {
                Tabla t = new Tabla();
                t.setEquipo(x);
                t.setGf(0);
                t.setPg(0);
                t.setPp(0);
                t.setPe(0);
                t.setGc(0);
                t.setPj(0);
                t.setPuntos(0);
                t.setTemporada(te);
                tabla.save(t);
            });
        }
        return te.getTablaList()
                .stream()
                .sorted(Comparator.comparingInt(Tabla::getPuntos)
                        .thenComparing((x, y) -> dg(x).compareTo(dg(y))).reversed())
                .collect(Collectors.toList());
    }

    Integer dg(Tabla t) {
        return t.getGf() - t.getGc();
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
        List<Resultado> re = new MethodsTable().tabla(p);
        Tabla t = te.getTablaList().stream()
                .filter(x -> Objects.equals(x.getEquipo().getId(), p.getEquipo1().getId()))
                .collect(Collectors.toList()).get(0);
        Tabla t1 = te.getTablaList().stream()
                .filter(x -> Objects.equals(x.getEquipo().getId(), p.getEquipo2().getId()))
                .collect(Collectors.toList()).get(0);
        tabla.save(resultado(t, re.get(0)));
        tabla.save(resultado(t1, re.get(1)));
        return true;
    }

    private Tabla resultado(Tabla t, Resultado r) {
        t.setGc(t.getGc() + r.getContra());
        t.setGf(t.getGf() + r.getFavor());
        t.setPuntos(t.getPuntos() + r.getPuntos());
        t.setPj(t.getPj() + 1);
        if (r.getPuntos() == 3)
        {
            t.setPg(t.getPg() + 1);
        } else if (r.getPuntos() == 1)
        {
            t.setPe(t.getPe() + 1);
        } else
        {
            t.setPp(t.getPp() + 1);
        }

        return t;
    }
}
