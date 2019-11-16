package com.liga.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liga.entidades.Tarjeta;
import com.liga.repositorios.ITarjeta;

@RestController
public class TarjetaController {
	@Autowired
	private ITarjeta tar;
	@RequestMapping(value="/lTarjeta", method=RequestMethod.GET)
	public String listadoTar(Model model) {
	model.addAttribute("tarjeta", tar.findAll());
	return "lTarjeta";
	}
	@RequestMapping(value = "/registrarT", method = RequestMethod.POST)
	public String registrarT(@ModelAttribute("Tarjeta") Tarjeta t, RedirectAttributes flash) { 
	flash.addFlashAttribute("exito","exito, Tarjeta guardado");
	tar.save(t);
	return "redirect:/lTarjeta";
	}
}
