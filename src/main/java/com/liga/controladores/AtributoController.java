package com.liga.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liga.entidades.Atributo;
import com.liga.repositorios.IAtributo;

import org.springframework.web.bind.annotation.ModelAttribute;



@RestController
public class AtributoController {
	@Autowired
	private IAtributo atr;
@RequestMapping(value="/lAtributo", method=RequestMethod.GET)
	public String listadoAtr(Model model) {
	model.addAttribute("atributo", atr.findAll());
	return "lAtributo";
}
@RequestMapping(value="registrarAtr", method=RequestMethod.POST)
 	public String registrarAtr(@ModelAttribute("atributo") Atributo at, RedirectAttributes flash){
	flash.addFlashAttribute("exito","exito, Atributo guardado");
	//atr.saveA(at);
    return "lAtributo";
}

}
