package com.liga.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liga.entidades.Gol;
import com.liga.repositorios.IGol;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/gol")
public class GolController {

    @Autowired
    private IGol gol;
    @PostMapping(value = "/Add")
    public Gol registrar(@RequestBody Gol g) {
        Gol c = g;
        c.setHora(Timestamp.valueOf(LocalDateTime.now()));
        return gol.save(c);
    }
}
