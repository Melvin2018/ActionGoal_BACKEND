package com.liga.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.liga.entidades.Lugar;
import com.liga.repositorios.ILugar;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
