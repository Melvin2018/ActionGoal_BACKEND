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
import com.liga.entidades.Tarjeta;
import com.liga.entidades.Temporada;
import com.liga.entidades.extras.Cronologia;
import com.liga.repositorios.IEquipoTemporada;
import com.liga.repositorios.IHorario;
import com.liga.repositorios.IJornada;
import com.liga.repositorios.IPartido;
import com.liga.repositorios.ITemporada;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/partido")
public class PartidosController {

    @Autowired
    private IHorario hora;
    @Autowired
    private IJornada jor;
    @Autowired
    private IPartido par;
    @Autowired
    private IEquipoTemporada eq;
    @Autowired
    private ITemporada tempo;

    public Temporada ultima() {
        return tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
    }
    private final List<EquipoTemporada> descanso = new ArrayList<>();
    private final List<Partido> partidos = new ArrayList<>();
    private final List<Jornada> jornadas = new ArrayList<>();
    private int contador = 0;
    private boolean impar = true;

    @GetMapping(value = "/Generar")
    public List<Partido> generar() {
        Temporada te = this.ultima();
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
            j.setEstado(1);
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
        jornadas.forEach(x -> jor.save(x));
        return partidos;
    }

    private Boolean JorAdd(Jornada x) {
        List<Partido> parp = new ArrayList<>();
        int contador1 = 0;
        while (parp.isEmpty())
        {
            contador1++;
            parp = partidos(x, equipos(x));
            if (contador1 == (3 * equipos(x).size() / 2))
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

    @GetMapping(value = "/FindBy/{ID}")
    public Partido buscar(@PathVariable("ID") Integer id) {
        Optional<Partido> p = par.findById(id);
        if (p.isPresent())
        {
            return p.get();
        }
        return null;
    }

    @GetMapping(value = "/iniciar/{ID}")
    public Partido iniciar(@PathVariable("ID") Integer id) {
        Optional<Partido> p = par.findById(id);
        if (p.isPresent())
        {
            p.get().setDescanso(0);
            p.get().setFecha(Timestamp.valueOf(LocalDateTime.now()));
            return par.save(p.get());
        }
        return null;
    }

    @GetMapping(value = "/descanso/{ID}")
    public Partido descanso(@PathVariable("ID") Integer id) {
        Optional<Partido> p = par.findById(id);
        if (p.isPresent())
        {
            p.get().setDescanso(1);
            return par.save(p.get());
        }
        return null;
    }

    @GetMapping(value = "/reanudar/{ID}")
    public Partido reanudar(@PathVariable("ID") Integer id) {
        Optional<Partido> p = par.findById(id);
        if (p.isPresent())
        {
            p.get().setDescanso(2);
            p.get().setSegundot(Timestamp.valueOf(LocalDateTime.now()));
            return par.save(p.get());
        }
        return null;
    }

    @GetMapping(value = "/finalizar/{ID}")
    public Partido finalizar(@PathVariable("ID") Integer id) {
        Optional<Partido> p = par.findById(id);
        if (p.isPresent())
        {
            p.get().setDescanso(3);
            p.get().setFinalizado(Timestamp.valueOf(LocalDateTime.now()));
            Jornada j = p.get().getJornada();
            if (j.getPartidoList().get(j.getPartidoList().size() - 1).getId() == p.get().getId())
            {
                j.setEstado(2);
            }
            return par.save(p.get());
        }
        return null;
    }
 
    @GetMapping(value = "/mensaje/{ID}")
    public List<Cronologia> mensaje(@PathVariable("ID") Integer id) {
        Partido p = par.getOne(id);
        List<Cronologia> lista = new ArrayList<>();
        p.getCambioList().forEach(x ->
        {
            Cronologia c = new Cronologia();
            boolean equipo = Objects.equals(x.getEntrante().getEquipo().getId(), p.getEquipo1().getId());
            c.setEquipo(equipo);
            c.setMinuto(x.getMinuto());
            c.setDato(x.getRazon());
            c.setJugador1(x.getEntrante().getJugador());
            c.setJugador2(x.getSaliente().getJugador());
            c.setTipo("cambio");
            lista.add(c);
        });
        List<Tarjeta> tar = new ArrayList<>();
        p.getTarjetaList().stream().sorted((x, y) -> x.getMinuto().compareTo(y.getMinuto())).forEach(x ->
        {
            tar.add(x);
            Cronologia c = new Cronologia();
            boolean equipo = Objects.equals(x.getCarnet().getEquipo().getId(), p.getEquipo1().getId());
            c.setEquipo(equipo);
            c.setMinuto(x.getMinuto());
            if (x.getTipo())
            {
                if (tar.stream().filter(a -> a.getCarnet().getId() == x.getCarnet().getId()).count() > 1)
                {
                    c.setTipo("roja y amarilla");
                } else
                {
                    c.setTipo("tarjeta roja");
                }
            } else
            {
                if (tar.stream().filter(a -> Objects.equals(a.getCarnet().getId(), x.getCarnet().getId())).count() > 1)
                {
                    c.setTipo("doble amarilla");
                } else
                {
                    c.setTipo("tarjeta amarilla");
                }
            }
            c.setJugador1(x.getCarnet().getJugador());
            lista.add(c);
        });
        p.getGolList().forEach(x ->
        {
            Cronologia c = new Cronologia();
            boolean equipo = false;
            if(Objects.equals(x.getCarnet().getEquipo().getId(), p.getEquipo1().getId())){
                if(x.getTipo()){
                equipo=true;
                }
            }else{
               if(!x.getTipo()){
                equipo=true;
                }
            }
            c.setEquipo(equipo);
            c.setMinuto(x.getMinuto());
            c.setTipo("gol");
            c.setDato(x.getForma());
            c.setJugador1(x.getCarnet().getJugador());
            lista.add(c);
        });
        return lista.stream().sorted((x, y) -> y.getMinuto().compareTo(x.getMinuto())).collect(Collectors.toList());
    }
}
