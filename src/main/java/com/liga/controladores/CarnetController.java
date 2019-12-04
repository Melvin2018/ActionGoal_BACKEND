package com.liga.controladores;

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
import com.liga.entidades.extras.ID1;
import com.liga.repositorios.ICarnet;
import com.liga.repositorios.IEquipoTemporada;
import com.liga.repositorios.IFormacion;
import com.liga.repositorios.IJugador;
import java.util.Objects;

@RestController
@RequestMapping(value = "/carnet")
public class CarnetController {

    @Autowired
    private ICarnet car;
    @Autowired
    private IEquipoTemporada eq;
    @Autowired
    private IJugador jugador;
    @Autowired
    private IFormacion formacion;

    @GetMapping(value = "/All")
    public List<EquipoTemporada> listado() {
        return eq.findAll();
    }

    @PostMapping(value = "/validar")
    public Boolean validard(@RequestBody ID1 entity) {
        EquipoTemporada et = eq.getOne(entity.getEquipo());
        return et.getCarnetList().stream().noneMatch((c) -> (Objects.equals(c.getDorsal(), entity.getDorsal())));
    }

    @PostMapping(value = "/Add")
    public Carnet registrar(@RequestBody ID1 entity) {
        EquipoTemporada et = eq.getOne(entity.getEquipo());
        for (Carnet c : et.getCarnetList())
        {
            if (Objects.equals(c.getDorsal(), entity.getDorsal()))
            {
                return null;
            }
        }
        Carnet c = new Carnet();
        c.setDorsal(entity.getDorsal());
        c.setEquipo(et);
        c.setJugador(jugador.getOne(entity.getJugador()));
        return car.save(c);
    }

    @GetMapping(value = "/jugadorDentro/{ID}")
    public List<Carnet> dentro(@PathVariable("ID") Integer id) {
        Optional<EquipoTemporada> et = eq.findById(id);
        List<Carnet> jugadores = new ArrayList<>();
        if (et.isPresent())
        {
            jugadores = et.get().getCarnetList();
        }
        return jugadores;
    }

    @GetMapping(value = "/FindAll/{ID}")
    public List<Carnet> find(@PathVariable("ID") Integer id) {
        Optional<EquipoTemporada> et = eq.findById(id);
        List<Carnet> jugadores = new ArrayList<>();
        if (et.isPresent())
        {
            jugadores = et.get().getCarnetList();
        }
        return jugadores;
    }

    @GetMapping(value = "/FindAll/formacion/{ID}")
    public List<Carnet> findForm(@PathVariable("ID") Integer id) {
        Formacion et = formacion.getOne(id);
        List<Carnet> jugadores = et.getEquipo().getCarnetList();
        List<Carnet> posibles = new ArrayList<>();
        et.getFormacionCarnetList().forEach(x ->
        {
            jugadores.removeIf(t -> Objects.equals(t.getId(), x.getJugador().getId()));
        });
        return posibles;
    }
     @GetMapping(value = "/FindAll/formacion1/{ID}")
    public List<Carnet> findForm1(@PathVariable("ID") Integer id) {
        Formacion et = formacion.getOne(id);
          List<Carnet> dentro = new ArrayList<>();
          et.getFormacionCarnetList().forEach(x->{
          dentro.add(x.getJugador());
          });
        return dentro;
    }
}
