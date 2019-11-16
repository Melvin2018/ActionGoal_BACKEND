package com.liga.controladores;

import java.util.ArrayList;
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
import com.liga.entidades.EquipoTemporada;
import com.liga.entidades.Temporada;
import com.liga.entidades.extras.ET;
import com.liga.entidades.extras.ID;
import com.liga.repositorios.IEquipo;
import com.liga.repositorios.IEquipoTemporada;
import com.liga.repositorios.IJugador;
import com.liga.repositorios.ITemporada;
import java.util.Objects;
 
@CrossOrigin(origins="http://192.168.43.17:8081", maxAge=3600)
@RestController
@RequestMapping(value="/equipoT")
public class EquipoTemporadaController {
	@Autowired
	private IEquipoTemporada eq;
	@Autowired
	private IEquipo equipo;
	@Autowired
	private IJugador jugador;
	@Autowired
	private ITemporada tempo;

	@GetMapping(value="/restantes")
	public List<Equipo> restante() {	
            Temporada tem=tempo.findAll().stream().max((x,y)->x.getNumero().compareTo(y.getNumero())).get();
		List<EquipoTemporada> equipt=tem.getEquipoTemporadaList();
		List<Equipo> equipos=new ArrayList<>();
                equipo.findAll().stream().filter((e) -> (equipt.stream().filter(x->x.getEquipo().getId().equals(e.getId())).count()==0)).forEach((e) -> {
                    equipos.add(e);
            });
	return equipos;
	}
	@GetMapping(value="/dentro")
	public List<ET> dentro() {
		Temporada tem=tempo.findAll().stream().max((x,y)->x.getNumero().compareTo(y.getNumero())).get();
		List<ET> et= new ArrayList<>();
		tem.getEquipoTemporadaList().forEach(x->{
			et.add(new ET(x,x.getCarnetList().size()));
		});
		return et;
	}
	@GetMapping(value="/All")
	public List<EquipoTemporada> listado() {
		return eq.findAll();
	}
	@PostMapping(value = "/Add")
	public EquipoTemporada registrar(@RequestBody ID entity) { 
            Temporada t= tempo.findAll().stream().max((x,y)->x.getNumero().compareTo(y.getNumero())).get();
            if(t.getEquipoTemporadaList().stream().filter(x->Objects.equals(x.getEquipo().getId(), entity.getEquipo())).count()<=0){
		EquipoTemporada tem= new EquipoTemporada();
		tem.setEquipo(equipo.getOne(entity.getEquipo()));
		tem.setTemporada(t);
		tem.setRepresentante(jugador.getOne(entity.getJugador()));
		return eq.save(tem);
            }
            return null;
	}
	@GetMapping(value = "/FindBy/{ID}")
	public EquipoTemporada findBy(@PathVariable(value="ID") int id) {
		return eq.getOne(id);
	}
     @PutMapping(value = "/Update/{ID}")
	public EquipoTemporada editar(@RequestBody @Valid EquipoTemporada t,@PathVariable(value="ID") int id) {
            t.setId(id);
            return eq.save(t);
	}
     @DeleteMapping(value = "/Delete/{ID}")
 	public boolean eliminar(@PathVariable(name="ID") int e) { 
    	 EquipoTemporada et=eq.getOne(e);
 		if(et.getCarnetList().isEmpty()) {
 			eq.deleteById(e);
 			return true;
 		}
 		return false;
 	}
}