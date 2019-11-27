package com.liga.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liga.entidades.EquipoTemporada;
import com.liga.entidades.Tabla;
import com.liga.entidades.Temporada;
import com.liga.entidades.extras.TemporadaA;
import com.liga.repositorios.ITemporada;

@RestController
@RequestMapping(value = "/temporada")
public class TemporadaController {

    @Autowired
    private ITemporada tempo;

    @GetMapping(value = "/All")
    public List<Temporada> listado() {
        return tempo.findAll();
    }

    @GetMapping(value = "/val")
    public Boolean validar() {
        List<Temporada> temporadas = tempo.findAll();
        if (!temporadas.isEmpty())
        {
            Temporada te = temporadas.stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
            return te.getEstado() == 3;
        }
        return true;
    }

    @GetMapping(value = "/showa")
    public Boolean showa() {
        Temporada tem = tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
        return tem.getEstado() == 1;
    }

    @GetMapping(value = "/showg")
    public Boolean showg() {
        Temporada tem = tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
        return tem.getEstado() == 1
                & tem.getEquipoTemporadaList().size() > 9
                & tem.getJornadaList().isEmpty();
    }

    @GetMapping(value = "/antiguo")
    public List<TemporadaA> Antiguo() {
        List<Temporada> temporadas = tempo.findAll();
        List<TemporadaA> temFinal = new ArrayList<>();
        TemporadaA ta;
        if (!temporadas.isEmpty())
        {
            Temporada te = temporadas.stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
            boolean b = te.getEstado()!=3;
            if (b)
            {
                temporadas.remove(te);
            }
            for (Temporada t : temporadas)
            {
                ta = new TemporadaA();
                List<Tabla> tablas = t.getTablaList();
                EquipoTemporada campeon = tablas.stream().max((x, y) -> x.getPuntos().compareTo(y.getPuntos())).get().getEquipo();
                Date fechaf = t.getJornadaList().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get().
                        getPartidoList().stream().max((x, y) -> y.getFecha().compareTo(x.getFecha())).get().getFecha();
                temFinal.add(ta);
                ta.setFf(fechaf);
                ta.setCampeon(campeon);
                ta.setNumeroe(t.getEquipoTemporadaList().size());
                temFinal.add(ta);
            }
        }
        return temFinal;
    }

    @PostMapping(value = "/Add")
    public Temporada registrar(@RequestBody Temporada tem) {
        List<Temporada> temporadas = tempo.findAll();
        boolean b = false;
        if (!temporadas.isEmpty())
        {
            Temporada te = temporadas.stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
            b = te.getEstado()==3;
            tem.setNumero(te.getNumero() + 1);
        }
        if (b)
        {
            tem.setEstado(1);
            return tempo.save(tem);
        }
        return null;
    }

    @GetMapping(value = "/FindBy/{ID}")
    public Temporada findBy(@PathVariable(value = "ID") int id) {
        return tempo.getOne(id);
    }
    @PutMapping(value = "/Update/{ID}")
    public Temporada editar(@RequestBody @Valid Temporada t, @PathVariable(value = "ID") int id) {
        t.setId(id);
        return tempo.save(t);
    }
}
