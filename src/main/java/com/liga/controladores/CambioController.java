package com.liga.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liga.entidades.Cambio;
import com.liga.entidades.Partido;
import com.liga.repositorios.ICambio;
import com.liga.repositorios.IPartido;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/cambio")
public class CambioController {

    @Autowired
    private ICambio cambio;
    @Autowired
    private IPartido par;

    @PostMapping(value = "/Add")
    public Cambio registrar(@RequestBody Cambio g) {
        Cambio c = g;
        c.setHora(Timestamp.valueOf(LocalDateTime.now()));
        return cambio.save(c);
    }

    @GetMapping(value = "/FindAll/{ID}")
    public List<Cambio> buscar(@PathVariable("ID") Integer id) {
        Optional<Partido> p = par.findById(id);
        if (p.isPresent()) {
            return p.get().getCambioList();
        }
        return null;
    }
}
