package com.liga.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.liga.entidades.Lugar;
import com.liga.repositorios.ILugar;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins="http://192.168.43.17:8081", maxAge=3600)
@RestController
@RequestMapping(value="/lugar")
public class LugarController {
    @Autowired
    private ILugar lug;       
    @GetMapping(value="/All")
      public List<Lugar> listado() {
         return lug.findAll();
      }
}
