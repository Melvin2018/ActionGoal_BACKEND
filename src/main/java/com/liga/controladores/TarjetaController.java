package com.liga.controladores;

import com.liga.entidades.Carnet;
import com.liga.entidades.Gol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liga.entidades.Partido;
import com.liga.entidades.Tarjeta;
import com.liga.entidades.extras.Conteo;
import com.liga.repositorios.ICarnet;
import com.liga.repositorios.IPartido;
import com.liga.repositorios.ITarjeta;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/tarjeta")
public class TarjetaController {

    @Autowired
    private ITarjeta tarjeta;
    @Autowired
    private IPartido par;
    @Autowired
    private ICarnet car;

    @PostMapping(value = "/Add")
    public Tarjeta registrar(@RequestBody Tarjeta g) {
        Tarjeta c = g;
        c.setHora(Timestamp.valueOf(LocalDateTime.now()));
        return tarjeta.save(c);
    }

    @GetMapping(value = "/FindAll/{ID}")
    public List<Tarjeta> buscar(@PathVariable("ID") Integer id) {
        Optional<Partido> p = par.findById(id);
        if (p.isPresent())
        {
            return p.get().getTarjetaList();
        }
        return null;
    }
}
