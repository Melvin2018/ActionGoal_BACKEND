package com.liga.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.liga.entidades.Gol;
import com.liga.repositorios.IGol;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class GolController {
	@Autowired
	private IGol gl;
@RequestMapping(value="/lGol", method=RequestMethod.GET)
	public String listadoGl(Model model) {
	model.addAttribute("gol", gl.findAll());
    return "lGol";
}
@RequestMapping(value="/registrarG", method=RequestMethod.POST)
	public String registrarG(@ModelAttribute("gol") Gol g, RedirectAttributes flash) {
    flash.addFlashAttribute("exito","exito, Gol guardado");
    gl.save(g);
    return "redirect:/lGol";
}
}
