package com.liga.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liga.entidades.Formacion;
import com.liga.entidades.FormacionCarnet;
import com.liga.entidades.Partido;
import com.liga.repositorios.IFormacion;
import com.liga.repositorios.IPartido;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/formacion")
public class FormacionController {

    @Autowired
    private IFormacion formacion;
    @Autowired
    private IPartido par;

    @GetMapping(value = "/All")
    public List<Formacion> lista(){
        return formacion.findAll();
    }
    @PostMapping(value = "/Add")
    public Formacion registrar(@RequestBody Formacion form) {
        form.getFormacionCarnetList().forEach(x -> {
            x.setFormacion(form);
        });
        return formacion.save(form);
    }
    @GetMapping(value = "/FindAll/{ID}")
    public List<Formacion> buscar(@PathVariable("ID") Integer id) {
        final Optional<Partido> p = par.findById(id);
        if (p.isPresent()) {
            return p.get().getFormacionList();
        }
        return null;
    }

    @GetMapping(value = "/FindByID/{ID}")
    public List<FormacionCarnet> buscarID(@PathVariable("ID") Integer id) {
        final Optional<Formacion> p = formacion.findById(id);
        if (p.isPresent())
        {
            return p.get().getFormacionCarnetList();
        }
        return null;
    }
}
