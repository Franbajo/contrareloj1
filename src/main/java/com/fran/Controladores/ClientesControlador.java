package com.fran.Controladores;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fran.Entidad.Clientes;
import com.fran.Servicio.IClientesServicio;

@Controller 
@RequestMapping
public class ClientesControlador {

	@Autowired
	private IClientesServicio service;
	/*.................LISTAR.....................*/
	@GetMapping("/clienteslistar")
	public String listar(Model model) {
		List <Clientes> personas=service.listar();
		model.addAttribute("clientes", personas);
		return "clientes/index";
	}
	/*.................CREAR NUEVO.....................*/
	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("cliente", new Clientes());
		return"clientes/form";
	}

	@PostMapping("/clientesave")
	public String save(@Valid @ModelAttribute("cliente")  Clientes p, BindingResult result, 
			Model model, RedirectAttributes attribute) {
		if(result.hasErrors()) {
					
		System.out.println("Error al introducir los datos"+result.getAllErrors());
				model.addAttribute("cliente", p);	
				return"clientes/form"; 
			}
				service.save(p);
				attribute.addFlashAttribute("success", "Cliente guardado con exito!");
				return"redirect:/clienteslistar";
		}

	/*.................ACTUALIZAR.....................*/
	@GetMapping("/editarcliente/{id}")
	public String editar(@PathVariable long id, Model model, RedirectAttributes attribute) {
		Optional<Clientes>persona=service.listarId(id);
		model.addAttribute("cliente", persona);
		attribute.addFlashAttribute("success", "Datos actualizados!");
	return"clientes/form";
}

	/*.................BORRAR.....................*/
	
	@GetMapping("/eliminarcliente/{id}")
	public String delete(Model model, @PathVariable long id, RedirectAttributes attribute) {
	service.delete(id);
	attribute.addFlashAttribute("warning", "ATENCIÓN: registro eliminado con exito!");
	return"redirect:/clienteslistar";
}

}
