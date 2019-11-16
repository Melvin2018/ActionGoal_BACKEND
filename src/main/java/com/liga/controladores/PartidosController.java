package com.liga.controladores;

import com.liga.entidades.EquipoTemporada;
import com.liga.entidades.Horario;
import com.liga.entidades.Jornada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.liga.entidades.Partido;
import com.liga.entidades.Temporada;
import com.liga.repositorios.IEquipoTemporada;
import com.liga.repositorios.IHorario;
import com.liga.repositorios.IJornada;
import com.liga.repositorios.IPartido;
import com.liga.repositorios.ITemporada;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://192.168.43.17:8081", maxAge = 3600)
@RestController
@RequestMapping(value = "/partido")
public class PartidosController {

    @Autowired
    private IPartido par;
    @Autowired
    private IHorario hora;
    @Autowired
    private ITemporada tempo;
    @Autowired
    private IJornada jor;
    @Autowired
    private IEquipoTemporada eq;
    
    @GetMapping(value = "/Generar")
    public List<Partido> partidos() {
        Temporada te = tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
        List<EquipoTemporada> equipos = te.getEquipoTemporadaList();
        List<Jornada> jornadas = new ArrayList<>();
        List<Partido> partidos = new ArrayList<>();

        AtomicInteger numero = new AtomicInteger(1);
        while (numero.get() < equipos.size())
        {
            Jornada j = new Jornada();
            j.setEstado(true);
            j.setNumero(numero.incrementAndGet());
            j.setTemporada(te);
            jornadas.add(jor.save(j));
        }
        jornadas.stream().forEach((x) ->
        {
            List<EquipoTemporada> equiposr = te.getEquipoTemporadaList();
            List<Horario> horarios = hora.findAll();
            while (equiposr.size() >= 2)
            {
                EquipoTemporada equipor1 = random(equiposr);
                equiposr.remove(equipor1);
                EquipoTemporada equipor2 = random(equiposr);
                equiposr.remove(equipor2);
                Horario horarior = random1(horarios);
                Partido partido = new Partido();
                partido.setEquipo1(equipor1);
                partido.setEquipo2(equipor2);
                partido.setEstado(Boolean.TRUE);
                partido.setJornada(x);
                partido.setHorario(horarior);
                partidos.add(par.save(partido));
            }
        });
        return partidos;
    }

    private EquipoTemporada random(List<EquipoTemporada> equipo) {
        Random random = new Random();
        int num = random.nextInt(equipo.size());
        return equipo.get(num);
    }

    private Horario random1(List<Horario> h) {
        Random random = new Random();
        int num = random.nextInt(h.size());
        return h.get(num);
    }
}
