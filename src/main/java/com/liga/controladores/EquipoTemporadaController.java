package com.liga.controladores;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liga.entidades.Equipo;
import com.liga.entidades.EquipoTemporada;
import com.liga.entidades.Temporada;
import com.liga.entidades.extras.ET;
import com.liga.repositorios.IEquipo;
import com.liga.repositorios.IEquipoTemporada;
import com.liga.repositorios.ITemporada;

@RestController
@RequestMapping(value = "/equipoT")
public class EquipoTemporadaController {

    @Autowired
    private IEquipoTemporada eq;
    @Autowired
    private IEquipo equipo;
    @Autowired
    private ITemporada tempo;

    public Temporada ultima() {
        return tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
    }

    @GetMapping(value = "/restantes")
    public List<Equipo> restante() {
        Temporada tem = this.ultima();
        List<EquipoTemporada> equipt = tem.getEquipoTemporadaList();
        List<Equipo> equipos = new ArrayList<>();
        equipo.findAll().stream().filter((e) -> (equipt.stream().filter(x -> x.getEquipo().getId().equals(e.getId())).count() == 0)).forEach((e) ->
        {
            equipos.add(e);
        });
        return equipos;
    }

    @GetMapping(value = "/dentro")
    public List<ET> dentro() {
        Temporada tem = this.ultima();
        List<ET> et = new ArrayList<>();
        tem.getEquipoTemporadaList().forEach(x ->
        {
            et.add(new ET(x, x.getCarnetList().size()));
        });
        return et;
    }

    @GetMapping(value = "/All")
    public List<EquipoTemporada> listado() {
        Temporada tem = this.ultima();
        return tem.getEquipoTemporadaList();
    }

    @PostMapping(value = "/Add")
    public EquipoTemporada registrar(@RequestBody EquipoTemporada entity) {
        Temporada t = this.ultima();
        entity.setTemporada(t);
        return eq.save(entity);
    }

    @GetMapping(value = "/FindBy/{ID}")
    public EquipoTemporada findBy(@PathVariable(value = "ID") int id) {
        return eq.getOne(id);
    }
}
