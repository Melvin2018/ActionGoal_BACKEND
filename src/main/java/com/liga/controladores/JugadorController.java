package com.liga.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liga.entidades.Carnet;
import com.liga.entidades.Equipo;
import com.liga.entidades.EquipoTemporada;
import com.liga.entidades.Jugador;
import com.liga.entidades.Lugar;
import com.liga.entidades.Temporada;
import com.liga.repositorios.IEquipo;
import com.liga.repositorios.IEquipoTemporada;
import com.liga.repositorios.IJugador;
import com.liga.repositorios.ITemporada;
import java.util.Objects;
import java.util.function.Predicate;

@CrossOrigin(origins = "http://192.168.43.17:8081", maxAge = 3600)
@RestController
@RequestMapping(value = "/Jugador")
public class JugadorController {

    @Autowired
    private IJugador jug;
    @Autowired
    private IEquipoTemporada eq;
    @Autowired
    private IEquipo equipo;
    @Autowired
    private ITemporada tempo;

    @GetMapping(value = "/restantes/{ID}")
    public List<Jugador> restante(@PathVariable("ID") Integer id) {
        Optional<Equipo> et = equipo.findById(id);
        Temporada te = tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
        List<Jugador> personas = new ArrayList<>();
        if (et.isPresent())
        {
            Lugar lu = et.get().getLugar();
            Predicate<EquipoTemporada> pre = x -> Objects.equals(x.getEquipo().getLugar().getId(), lu.getId());
            personas = jug.findAll().stream().filter(x -> Objects.equals(x.getPersona().getLugar().getId(), lu.getId())).collect(Collectors.toList());
            if (te.getEquipoTemporadaList().stream().filter(pre).count() >= 1)
            {
                for (EquipoTemporada eqt : te.getEquipoTemporadaList().stream().filter(pre).collect(Collectors.toList()))
                {
                    personas.removeIf(s -> Objects.equals(s.getId(), eqt.getRepresentante().getId()));
                    for (Carnet c : eqt.getCarnetList())
                    {
                        personas.removeIf(s -> Objects.equals(s.getId(), c.getJugador().getId()));
                    }
                }
            }
        }
        return personas;
    }

    @GetMapping(value = "/jugadores/{ID}")
    public List<Jugador> dentro(@PathVariable("ID") Integer id) {
        Optional<EquipoTemporada> et = eq.findById(id);
        List<Jugador> jugadores = new ArrayList<>();
        Temporada te = tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
        if (et.isPresent())
        {
            EquipoTemporada equipotempo = et.get();
            Lugar lu = equipotempo.getEquipo().getLugar();
            Predicate<EquipoTemporada> pre = x -> Objects.equals(x.getEquipo().getLugar().getId(), lu.getId());
            Predicate<Jugador> preJug = x -> Objects.equals(x.getPersona().getLugar().getId(), lu.getId());
            jugadores = jug.findAll().stream().filter(preJug).collect(Collectors.toList());
            if (te.getEquipoTemporadaList().stream().filter(pre).count() >= 1)
            {
                for (EquipoTemporada eqt : te.getEquipoTemporadaList().stream().filter(pre).collect(Collectors.toList()))
                {
                    jugadores.removeIf(s -> Objects.equals(s.getId(), eqt.getRepresentante().getId()));
                    for (Carnet c : eqt.getCarnetList())
                    {
                        jugadores.removeIf(s -> Objects.equals(s.getId(), c.getJugador().getId()));
                    }
                }
            }
            if (equipotempo.getCarnetList().stream().filter(x -> Objects.equals(x.getJugador().getId(), equipotempo.getRepresentante().getId())).count() == 0)
            {
                jugadores.add(equipotempo.getRepresentante());
            }
        }
        return jugadores;
    }

    @GetMapping(value = "/All")
    public List<Jugador> listado() {
        return jug.findAll();
    }

    @PostMapping(value = "/Add")
    public Jugador registrar(@RequestBody Jugador j) {
        return jug.save(j);
    }

    @GetMapping(value = "/FindBy/{ID}")
    public Jugador findBy(@PathVariable(value = "ID") int id) {
        return jug.getOne(id);
    }

    @PutMapping(value = "/Update")
    public Jugador editar(@RequestBody @Valid Jugador j) {
        return jug.save(j);
    }

    @DeleteMapping(value = "/Delete/{ID}")
    public Boolean eliminar(@PathVariable(name = "ID") int e) {
        Jugador j = jug.findById(e).get();
        if (j.getCarnetList().isEmpty())
        {
            jug.deleteById(e);
            return true;
        }
        return false;
    }
}
