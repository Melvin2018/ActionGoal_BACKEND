package com.liga.controladores;


import com.liga.entidades.Jornada;
import com.liga.entidades.Temporada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.liga.repositorios.ITemporada;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://192.168.43.17:8081", maxAge = 3600)
@RestController
@RequestMapping(value = "/jornada")
public class JornadasController {
	@Autowired
	private ITemporada tempo;
        @GetMapping(value="/All")
        public List<Jornada> jornadas(){
             Temporada te = tempo.findAll().stream().max((x, y) -> x.getNumero().compareTo(y.getNumero())).get();
            return te.getJornadaList();
        }
   
}