package com.liga.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.liga.entidades.Tabla;
import com.liga.entidades.Temporada;
import com.liga.repositorios.ITemporada;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value="/tabla")
public class TablaController {
    @Autowired
    private ITemporada tempo;
    @GetMapping(value="/All")
      public List<Tabla> listado() {
           Temporada te = tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
           return te.getTablaList().stream().sorted((x,y)->x.getPuntos().compareTo(y.getPuntos())).collect(Collectors.toList());
      }
}
