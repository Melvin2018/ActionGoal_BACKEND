package com.liga.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liga.entidades.Gol;
import com.liga.entidades.Partido;
import com.liga.repositorios.ICarnet;
import com.liga.repositorios.IGol;
import com.liga.repositorios.IPartido;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/gol")
public class GolController {

    @Autowired
    private IGol gol;
    @Autowired
    private IPartido par;

    @PostMapping(value = "/Add")
    public Gol registrar(@RequestBody Gol g) {
        Gol c = g;
        c.setHora(Timestamp.valueOf(LocalDateTime.now()));
        return gol.save(c);
    }

    @GetMapping(value = "/FindAll/{ID}")
    public List<Gol> buscar(@PathVariable("ID") Integer id) {
        Optional<Partido> p = par.findById(id);
        if (p.isPresent())
        {
            return p.get().getGolList();
        }
        return null;
    }

    @GetMapping(value = "/Number/{ID}/{N}")
    public Integer goles(@PathVariable("ID") Integer id, @PathVariable("N") Integer n) {
        Optional<Partido> p = par.findById(id);
        int num = 0;
        if (p.isPresent())
        {
            if (n == 1)
            {
                num += (int) p.get().getGolList().stream().filter(
                        x -> x.getTipo() & Objects.equals(x.getCarnet().getEquipo().getId(), x.getPartido().getEquipo1().getId()))
                        .count();
                num += (int) p.get().getGolList().stream().filter(
                        x -> !x.getTipo() & Objects.equals(x.getCarnet().getEquipo().getId(), x.getPartido().getEquipo2().getId()))
                        .count();
            } else
            {
                num += (int) p.get().getGolList().stream().filter(
                        x -> !x.getTipo() & Objects.equals(x.getCarnet().getEquipo().getId(), x.getPartido().getEquipo1().getId()))
                        .count();
                num += (int) p.get().getGolList().stream().filter(
                        x -> x.getTipo() & Objects.equals(x.getCarnet().getEquipo().getId(), x.getPartido().getEquipo2().getId()))
                        .count();
            }

        }
        return num;
    }
}
