package com.liga.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liga.entidades.EquipoTemporada;
import com.liga.entidades.Horario;
import com.liga.entidades.Jornada;
import com.liga.entidades.Partido;
import com.liga.entidades.Temporada;
import com.liga.repositorios.IEquipoTemporada;
import com.liga.repositorios.IHorario;
import com.liga.repositorios.IJornada;
import com.liga.repositorios.IPartido;
import com.liga.repositorios.ITemporada;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/partido")
public class PartidosController {

    @Autowired
    private IHorario hora;
    @Autowired
    private ITemporada tempo;
    @Autowired
    private IJornada jor;
    @Autowired
    private IPartido par;
    @Autowired
    private IEquipoTemporada eq;
    private List<EquipoTemporada> descanso = new ArrayList<>();
    private List<Partido> partidos = new ArrayList<>();
    private boolean repetir=false;

    @GetMapping(value = "/Generar")
    public List<Partido> partidos() {
        Temporada te = tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
        List<Jornada> jornadas = new ArrayList<>();

        AtomicInteger numero = new AtomicInteger(0);
        while (numero.incrementAndGet() < te.getEquipoTemporadaList().size())
        {
            Jornada j = new Jornada();
            j.setEstado(true);
            j.setNumero(numero.get());
            j.setTemporada(te);
            jornadas.add(j);
        }
        jornadas.stream().forEach((x) ->
        {
            JorAdd(x);
        });
        return partidos;
    }

    private void JorAdd(Jornada x) {
        List<EquipoTemporada> equiposr = eq.findAll().stream().filter(y -> y.getTemporada().getId().equals(x.getTemporada().getId())).collect(Collectors.toList());
        List<Horario> horariosr = hora.findAll();
        if (equiposr.size() % 2 == 1)
        {
            EquipoTemporada eqt = random(descanso, x.getTemporada());
            descanso.add(eqt);
            equiposr.remove(eqt);
        }
        while (equiposr.size() >= 2)
        {
            System.out.println("jornada " + x.getNumero() + " " + equiposr.size());
            EquipoTemporada equipor1 = random(equiposr);
            equiposr.remove(equipor1);
            EquipoTemporada equipor2 = random(equiposr, partidos, equipor1);
           
            if(repetir){
               descanso.remove(descanso.size()-1);
               JorAdd(x);
              break;
            }if(equipor2!=null){
            equiposr.remove(equipor2);
            Horario horarior = random1(horariosr);
            horariosr.remove(horarior);
            Partido partido = new Partido();
            partido.setEquipo1(equipor1);
            partido.setEquipo2(equipor2);
            partido.setEstado(Boolean.TRUE);
            partido.setJornada(x);
            partido.setHorario(horarior);
            partidos.add(partido);
            x.setPartidoList(partidos);
            jor.save(x);
            }
        }
    }

    private EquipoTemporada random(List<EquipoTemporada> eq) {
        Random random = new Random();
        int num = random.nextInt(eq.size());
        return eq.get(num);
    }

    private EquipoTemporada random(List<EquipoTemporada> des, Temporada te) {
        List<EquipoTemporada> equiposr = eq.findAll().stream().filter(y -> y.getTemporada().getId().equals(te.getId())).collect(Collectors.toList());
        des.forEach(x ->
        {
            equiposr.remove(x);
        });
        Random random = new Random();
        int num = random.nextInt(equiposr.size());
        return equiposr.get(num);
    }

    private EquipoTemporada random(List<EquipoTemporada> eqt, List<Partido> partidos, EquipoTemporada eq1) {
        boolean aprobado = false;
        while (!aprobado)
        {
            EquipoTemporada et = random(eqt);
            aprobado = partidos.isEmpty();
            for (Partido p : partidos)
            {
                aprobado = evaluar(p, eq1, et);
                if (!aprobado)
                {
                    if (eqt.size()<= 2)
                    {
                        aprobado=true;
                        repetir=true;
                    }
                    break;
                }
            }
            if (aprobado)
            {
                return et;
            }
        }
        return null;
    }

    private Boolean evaluar(Partido p, EquipoTemporada e1, EquipoTemporada e2) {
        if (Objects.equals(p.getEquipo1().getId(), e1.getId()) & Objects.equals(p.getEquipo2().getId(), e2.getId()))
        {
            System.out.println(p.getEquipo1().getEquipo().getNombre() + " vs " + p.getEquipo2().getEquipo().getNombre());
            return false;
        } else if (Objects.equals(p.getEquipo2().getId(), e1.getId()) & Objects.equals(p.getEquipo1().getId(), e2.getId()))
        {
            System.out.println(p.getEquipo1().getEquipo().getNombre() + " vs " + p.getEquipo2().getEquipo().getNombre());
            return false;
        }
        return true;
    }

    private Horario random1(List<Horario> h) {
        Random random = new Random();
        int num = random.nextInt(h.size());
        return h.get(num);
    }
}
