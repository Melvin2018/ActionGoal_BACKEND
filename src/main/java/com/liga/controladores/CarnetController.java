package com.liga.controladores;

import com.liga.entidades.Cambio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liga.entidades.Carnet;
import com.liga.entidades.EquipoTemporada;
import com.liga.entidades.Formacion;
import com.liga.entidades.Jugador;
import com.liga.entidades.Partido;
import com.liga.entidades.Tarjeta;
import com.liga.entidades.Temporada;
import com.liga.entidades.extras.Conteo;
import com.liga.repositorios.ICarnet;
import com.liga.repositorios.IEquipoTemporada;
import com.liga.repositorios.IPartido;
import com.liga.repositorios.ITemporada;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/carnet")
public class CarnetController {

    @Autowired
    private ICarnet car;
    @Autowired
    private IEquipoTemporada eq;
    @Autowired
    private IPartido partido;
    @Autowired
    private ITemporada tempo;

    public Temporada ultima() {
        return tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
    }

    @GetMapping(value = "/All")
    public List<EquipoTemporada> listado() {
        return eq.findAll();
    }

    @PostMapping(value = "/Add/{ID}")
    public Integer registrar(@RequestBody Carnet ca, @PathVariable("ID") Integer equipo) {
        EquipoTemporada et = eq.getOne(equipo);
        AtomicInteger num = new AtomicInteger(0);
        Predicate<EquipoTemporada> pre1 = x -> Objects.equals(et.getEquipo().getLugar().getId(), x.getEquipo().getLugar().getId());
        et.getTemporada().getEquipoTemporadaList().stream().filter(pre1).forEach(x ->
        {
            x.getCarnetList().stream().filter(y -> Objects.equals(y.getJugador().getId(), ca.getJugador().getId())).forEach(y ->
            {
                num.set(1);
            });
        });
        et.getCarnetList().stream().filter((c) -> (Objects.equals(c.getDorsal(), ca.getDorsal()))).forEach((x) ->
        {
            num.set(2);
        });
        if (num.get() == 0)
        {
            ca.setEquipo(et);
            car.save(ca);
        }
        return num.get();
    }

    @GetMapping(value = "/Delete/{ID}")
    public boolean eliminar(@PathVariable("ID") int e) {
        Carnet ca = car.getOne(e);
        if (ca.getFormacionCarnetList().isEmpty() | ca.getCambioList1().isEmpty())
        {
            car.deleteById(e);
            return true;
        }
        return false;
    }

    @GetMapping(value = "/FindAll/{ID}")
    public List<Carnet> dentro(@PathVariable("ID") Integer id) {
        EquipoTemporada et = eq.getOne(id);
        return et.getCarnetList();
    }

    @GetMapping(value = "/dentro/{ID}/{N}")
    public List<Carnet> findFormacion(@PathVariable("ID") Integer id, @PathVariable("N") Integer num) {
        Optional<Partido> par = partido.findById(id);
        List<Carnet> jugadores = new ArrayList<>();
        if (par.isPresent())
        {
            Formacion f;
            List<Cambio> cambios;
            List<Tarjeta> tarjetas;
            if (num == 1)
            {
                cambios = par.get().getCambioList().stream()
                        .filter(x -> Objects.equals(x.getEntrante().getEquipo().getId(),
                                        par.get().getEquipo1().getId())).collect(Collectors.toList());
                tarjetas = par.get().getTarjetaList().stream()
                        .filter(x -> Objects.equals(x.getCarnet().getEquipo().getId(),
                                        par.get().getEquipo1().getId())).collect(Collectors.toList());
                f = par.get().getFormacionList().get(0);
            } else
            {
                cambios = par.get().getCambioList().stream()
                        .filter(x -> Objects.equals(x.getEntrante().getEquipo().getId(),
                                        par.get().getEquipo2().getId())).collect(Collectors.toList());
                tarjetas = par.get().getTarjetaList().stream()
                        .filter(x -> Objects.equals(x.getCarnet().getEquipo().getId(),
                                        par.get().getEquipo2().getId())).collect(Collectors.toList());
                f = par.get().getFormacionList().get(1);
            }
            f.getFormacionCarnetList().stream().filter(x -> x.getTitular()).forEach(x ->
            {
                jugadores.add(x.getJugador());
            });
            if (!cambios.isEmpty())
            {
                cambios.forEach(y ->
                {
                    jugadores.remove(y.getSaliente());
                    jugadores.add(y.getEntrante());
                });
            }
            if (!tarjetas.isEmpty())
            {
                tarjetas.forEach(y ->
                {
                    if (y.getTipo())
                    {
                        jugadores.remove(y.getCarnet());
                    } else
                    {
                        if (tarjetas.stream()
                                .filter(s -> !s.getTipo() & Objects.equals(s.getCarnet().getId(), s.getCarnet().getId()))
                                .count() > 1)
                        {
                            jugadores.remove(y.getCarnet());
                        }
                    }
                });
            }
        }
        return jugadores;
    }

    @GetMapping(value = "/entrante/{ID}/{N}")
    public List<Carnet> Entrante(@PathVariable("ID") Integer id, @PathVariable("N") Integer num) {
        Optional<Partido> par = partido.findById(id);
        List<Carnet> jugadores = new ArrayList<>();
        if (par.isPresent())
        {
            Formacion f;
            List<Cambio> cambios;
            if (num == 1)
            {
                cambios = par.get().getCambioList().stream()
                        .filter(x -> Objects.equals(x.getEntrante().getEquipo().getId(),
                                        par.get().getEquipo1().getId())).collect(Collectors.toList());

                f = par.get().getFormacionList().get(0);
            } else
            {
                cambios = par.get().getCambioList().stream()
                        .filter(x -> Objects.equals(x.getEntrante().getEquipo().getId(),
                                        par.get().getEquipo2().getId())).collect(Collectors.toList());
                f = par.get().getFormacionList().get(1);
            }
            f.getFormacionCarnetList().stream().filter(x -> !x.getTitular()).forEach(x ->
            {
                jugadores.add(x.getJugador());
            });
            if (!cambios.isEmpty())
            {
                cambios.forEach(y ->
                {
                    jugadores.remove(y.getEntrante());
                });
            }
        }
        return jugadores;
    }

    @GetMapping(value = "/FindBy/{ID}")
    public Carnet findOne(@PathVariable("ID") Integer id) {
        return car.getOne(id);
    }

    public int tarjetas(Carnet c, boolean b) {
        return (int) c.getTarjetaList().stream().filter(x -> x.getTipo() == b).count();
    }

    public int goles(Carnet c) {
        return c.getGolList().size();
    }
    @GetMapping(value = "/conteo")
    public List<Conteo> conteo(){
        List<Carnet> jugadores = new ArrayList<>();
        List<Conteo> conteo = new ArrayList<>();
        this.ultima().getEquipoTemporadaList().forEach(x ->
        {
            x.getCarnetList().forEach(y ->
            {
                jugadores.add(y);
            });
        });
        jugadores.forEach(x ->
        {
            Conteo c = new Conteo();
            c.setJugador(x);
            c.setAmarillas(this.tarjetas(x, false));
            c.setRojas(this.tarjetas(x, true));
            c.setGoles(this.goles(x));
            conteo.add(c);
        });
        return conteo;
    }
    
    
}
