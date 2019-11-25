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
    private IEquipoTemporada eq;
    private final List<EquipoTemporada> descanso = new ArrayList<>();
    private final List<Partido> partidos = new ArrayList<>();
    private final List<Jornada> jornadas = new ArrayList<>();
    private int contador = 0;
    private boolean impar = true;

    @GetMapping(value = "/Generar")
    public List<Partido> generar() {
        Temporada te = tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
        if (!te.getJornadaList().isEmpty())
        {
            return null;
        }
        List<Jornada> jornadasr = new ArrayList<>();
        int cant = te.getEquipoTemporadaList().size();
        if (cant % 2 == 1)
        {
            cant++;
            impar = true;
        } else
        {
            impar = false;
        }

        AtomicInteger numero = new AtomicInteger(0);
        while (numero.incrementAndGet() != cant)
        {
            Jornada j = new Jornada();
            j.setEstado(true);
            j.setNumero(numero.get());
            j.setTemporada(te);
            jornadasr.add(j);
        }
        boolean repetir = false;
        do
        {
            for (Jornada j : jornadasr)
            {
                repetir = JorAdd(j);
                if (repetir)
                {
                    break;
                }
            }
        } while (repetir);
        jornadas.forEach(x->jor.save(x));
        return partidos;
    }

    private Boolean JorAdd(Jornada x) {
        List<Partido> parp = new ArrayList<>();
        int contador1 = 0;
        while (parp.isEmpty())
        {
            contador1++;
            parp = partidos(x, equipos(x));
            if (contador1 == equipos(x).size())
            {
                partidos.removeAll(partidos);
                descanso.removeAll(descanso);
                return true;
            }
        }
        partidos.addAll(parp);
        x.setPartidoList(parp);
        jornadas.add(x);
        return false;
    }

    private List<Partido> partidos(Jornada x, List<EquipoTemporada> equiposr) {
        List<Partido> pp = new ArrayList<>();
        List<Horario> horarios = hora.findAll();
        while (equiposr.size() > 1)
        {
            System.out.println("jornada " + x.getNumero() + " " + equiposr.size());
            EquipoTemporada equipor1 = random(equiposr);
            equiposr.remove(equipor1);
            EquipoTemporada equipor2 = random(equiposr, equipor1);
            if (equipor2 == null)
            {
                equiposr.removeAll(equiposr);
                pp.removeAll(pp);
                return pp;
            }
            equiposr.remove(equipor2);
            Horario horarior = random1(horarios);
            horarios.remove(horarior);
            Partido partido = new Partido();
            partido.setEquipo1(equipor1);
            partido.setEquipo2(equipor2);
            partido.setEstado(Boolean.TRUE);
            partido.setJornada(x);
            partido.setHorario(horarior);
            pp.add(partido);
        }
        if (impar)
        {
            EquipoTemporada etr = equiposr.get(0);
            if (!equipoDescanso(etr))
            {
                equiposr.removeAll(equiposr);
                pp.removeAll(pp);
                return pp;
            } else
            {
                descanso.add(etr);
                return pp;
            }
        }
        return pp;
    }

    private EquipoTemporada random(List<EquipoTemporada> eq) {
        Random random = new Random();
        int num = random.nextInt(eq.size());
        return eq.get(num);
    }

    private Horario random1(List<Horario> h) {
        Random random = new Random();
        int num = random.nextInt(h.size());
        return h.get(num);
    }

    private EquipoTemporada random(List<EquipoTemporada> eqt, EquipoTemporada eq1) {
        contador = 0;
        boolean aprobado = false;
        while (!aprobado)
        {
            contador++;
            EquipoTemporada et = random(eqt);
            aprobado = partidos.isEmpty();
            for (Partido p : partidos)
            {
                aprobado = evaluar(p, eq1, et);
                if (!aprobado)
                {
                    if (eqt.size() <= 2)
                    {
                        return null;
                    }
                    if (contador == eqt.size() - 1)
                    {
                        return null;
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

    private Boolean equipoDescanso(EquipoTemporada et) {
        return descanso.stream().noneMatch(x -> x.getId().equals(et.getId()));
    }

    private List<EquipoTemporada> equipos(Jornada x) {
        return eq.findAll().stream().filter(y -> y.getTemporada().getId().equals(x.getTemporada().getId())).collect(Collectors.toList());
    }
}
