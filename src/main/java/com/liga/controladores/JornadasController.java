package com.liga.controladores;

import com.liga.controladores.inteligencia.MethodsTable;
import com.liga.entidades.Jornada;
import com.liga.entidades.Partido;
import com.liga.entidades.Tabla;
import com.liga.entidades.Temporada;
import com.liga.entidades.extras.Partido1;
import com.liga.repositorios.ITabla;
import com.liga.repositorios.ITemporada;
import java.util.ArrayList;
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
    private ITemporada tempo;

    public Temporada ultima() {
        return tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
    }

    @GetMapping(value = "/All")
    public List<Partido1> jornadas() {
        Temporada te = this.ultima();
          List<Partido1> par=new ArrayList<>();
        te.getJornadaList().stream().sorted((x, y) -> x.getNumero().compareTo(y.getNumero())).forEach(j->{
            j.getPartidoList().stream().forEach((p) ->
            {
                par.add(pa(p));
            });
        });
        return par;
    }

    @GetMapping(value = "/FindLast")
    public List<Partido1> jornada() {
        Temporada te = this.ultima();
        List<Partido1> par=new ArrayList<>();
        List<Jornada> jo = te.getJornadaList().stream().filter(x -> x.getEstado() == 1).collect(Collectors.toList());
        if (!jo.isEmpty())
        {
            Jornada j = jo.stream().min((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
            j.getPartidoList().stream().forEach((p) ->
            {
                par.add(pa(p));
            });
        }
        return par;
    }

    Partido1 pa(Partido p) {
        Partido1 pa = new Partido1();
        pa.setJornada(p.getJornada());
        pa.setPartido(p);
        pa.setGol1(new MethodsTable().goles(p).get(0));
        pa.setGol2(new MethodsTable().goles(p).get(1));
        return pa;
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
