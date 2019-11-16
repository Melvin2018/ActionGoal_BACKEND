package com.liga.controladores;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liga.entidades.Equipo;
import com.liga.repositorios.IEquipo;

@CrossOrigin(origins="http://192.168.43.17:8081", maxAge=3600)
@RestController
@RequestMapping(value="/equipo")
public class EquipoController {
	@Autowired
	private IEquipo eq;
	
	@GetMapping(value="/All")
	public List<Equipo> listado() {
		return eq.findAll();
	}
	@PostMapping(value = "/Add")
	public Equipo registrar(@RequestBody @Valid Equipo j) {
           return eq.save(j);
	}
	@GetMapping(value = "/FindBy/{ID}")
	public Equipo findBy(@PathVariable(value="ID") int id) {
            return eq.getOne(id);
	}
        @PutMapping(value = "/Update")
	public Equipo editar(@RequestBody @Valid Equipo j) {
            return eq.save(j);
	}
	@DeleteMapping(value = "/Delete/{ID}")
	public boolean eliminar(@PathVariable(name="ID") int e) { 
		Equipo equipo=eq.getOne(e);
		if(equipo.getEquipoTList().isEmpty()) {
			eq.deleteById(e);
			return true;
		}
		return false;
	}
}