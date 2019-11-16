package com.liga.controladores;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.liga.entidades.Arbitro;
import com.liga.repositorios.IArbitro;
@RestController
public class ArbitroController {
	@Autowired
	private IArbitro arb;
	@RequestMapping(value="/lArbitro", method=RequestMethod.GET)
	public String listadoArb(Model model) {
		model.addAttribute("arbitro", arb.findAll());
		return "lArbitro";
	}
	@RequestMapping(value = "/registrarA", method = RequestMethod.POST)
	public String registrarA(@ModelAttribute("arbitro") Arbitro a, RedirectAttributes flash) { 
		flash.addFlashAttribute("exito","exito, arbitro guardado");
		arb.save(a);
		return "redirect:/lArbitro";
	}
		@GetMapping(value = "/editarA/{id}") //editar
		public String editarA(@PathVariable(value="id") int id,Model model) {
			Arbitro c= new Arbitro();
			c=arb.findById(id).orElseThrow(()-> new IllegalArgumentException("Id Incorrecto:" + id));
			model.addAttribute("arbitro",c);
			return "actualizarJ";	
	}
	@PostMapping(value = "/updateA/{id}") //actualizar
	public String actualizarA (@PathVariable(name="id") int id,@Valid Arbitro j, BindingResult result, Model model) {
        if (result.hasErrors()) {
        return "redirect:/jugador/edit{id}(id=".concat(String.valueOf(id).concat(")"));
        }
		arb.save(j);
		return "redirect:/jugador/index";
	}
	
	//actualizar
	//editar
	
}