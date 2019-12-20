package com.liga.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.liga.repositorios.IPosicion;
import com.liga.repositorios.ITemporada;
import java.util.Objects;
import java.util.function.Predicate;

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
    private IPosicion posi;

    @GetMapping(value = "/posibles/{ID}")
    public List<Jugador> representantes(@PathVariable("ID") Integer id) {
        Equipo et = equipo.getOne(id);
        return jug.findAll().stream().filter(x -> Objects.equals(x.getPersona().getLugar().getId(), et.getLugar().getId())).collect(Collectors.toList());
    }

    @GetMapping(value = "/posiblesc/{ID}")
    public List<Jugador> carnets(@PathVariable("ID") Integer id) {
        EquipoTemporada et = eq.getOne(id);
        Predicate<Jugador> pre = x -> Objects.equals(et.getEquipo().getLugar().getId(), x.getPersona().getLugar().getId());
        Predicate<EquipoTemporada> pre1 = x -> Objects.equals(et.getEquipo().getLugar().getId(), x.getEquipo().getLugar().getId());
        List<Jugador> personas = jug.findAll().stream().filter(pre).collect(Collectors.toList());
        et.getTemporada().getEquipoTemporadaList().stream().filter(pre1).forEach(x ->
        {
            x.getCarnetList().forEach(y ->
            {
                personas.removeIf(z -> Objects.equals(z.getId(), y.getJugador().getId()));
            });
        });
        return personas;
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

    @GetMapping(value = "/Delete/{ID}")
    public Boolean eliminar(@PathVariable(value = "ID") int e) {
        Jugador j = jug.findById(e).get();
        if (j.getCarnetList().isEmpty())
        {
            jug.deleteById(e);
            return true;
        }
        return false;
    }
}
