package com.liga.controladores;

import com.liga.entidades.Jornada;
import com.liga.entidades.Partido;
import com.liga.entidades.Tabla;
import com.liga.entidades.Temporada;
import com.liga.repositorios.ITabla;
import com.liga.repositorios.ITemporada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/jornada")
public class JornadasController {

    @Autowired
    private ITabla tab;
@Autowired
    private ITemporada tempo;

    public Temporada ultima() {
        return tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
    }
    @GetMapping(value = "/All")
    public List<Jornada> jornadas() {
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
                tab.save(t);
            });
        }
        return te.getJornadaList().stream().sorted((x, y) -> x.getNumero().compareTo(y.getNumero())).collect(Collectors.toList());
    }

    @GetMapping(value = "/FindLast")
    public Jornada jornada() {
        Temporada te = this.ultima();
        List<Jornada> jo = te.getJornadaList().stream().filter(x -> x.getEstado() == 1).collect(Collectors.toList());
        if (!jo.isEmpty())
        {
            return jo.stream().min((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
        }
        return null;
    }

    @GetMapping(value = "/Last")
    public List<Partido> partidos() {
        Temporada te = this.ultima();
        List<Jornada> jo = te.getJornadaList().stream().filter(x -> x.getEstado() == 1).collect(Collectors.toList());
        if (!jo.isEmpty())
        {
            Jornada j = jo.stream().min((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
            return j.getPartidoList();
        }
        return null;
    }
}
