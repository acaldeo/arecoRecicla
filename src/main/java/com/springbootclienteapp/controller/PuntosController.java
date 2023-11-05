package com.springbootclienteapp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootclienteapp.SpringbootClienteappApplication;
import com.springbootclienteapp.models.entity.Punto;
import com.springbootclienteapp.models.entity.Usuario;
import com.springbootclienteapp.models.service.IPuntosService;
import com.springbootclienteapp.models.service.IUsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/views/puntos")
public class PuntosController {
	
	@Autowired
	private IPuntosService puntosService;
	
	@Autowired
	private IUsuarioService usuarioService;

	private static final Logger logJava = Logger.getLogger(SpringbootClienteappApplication.class);

	@GetMapping({ "/" })
	public String ListarPuntos(Model model) {

		List<Punto> listadoPuntos = puntosService.Listartodos();
 		
		// Permite Mapear en la vista con thymeleaf por medio del atributo th:
		model.addAttribute("Titulo", "Puntos de Usuarios");
		model.addAttribute("puntos", listadoPuntos);
		logJava.debug("Cargando Puntos de Usuarios");

		return "/views/puntos/listar";
	}

	@GetMapping("/create")
	public String crear(Model model) {

		Punto puntos = new Punto();
		List<Usuario> listadoUsuarios = usuarioService.Listartodos();
		
		model.addAttribute("titulo", "formulario: Carga de Puntos");
		model.addAttribute("usuario", listadoUsuarios);
		model.addAttribute("punto", puntos);
		
		return "/views/puntos/frmCrear";
	}

	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Punto puntos, BindingResult result, Model model,
			RedirectAttributes mensaje) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "formulario: Carga de puntos");
			model.addAttribute("punto", puntos);
			
			return "/views/puntos/frmCrear";
		}

		puntosService.guardar(puntos);
		mensaje.addFlashAttribute("success", "Datos guardados con Exito!");
		
		return "redirect:/views/puntos/";
	}	

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idPunto, RedirectAttributes mensaje) {

		Punto punto = null;

		if (idPunto > 0) {

			punto = puntosService.buscarPorId(idPunto);

			if (punto == null) {

				mensaje.addFlashAttribute("error", "Error:El ID no existe!");
				return "redirect:/views/puntos/";
			}

		} else {

			mensaje.addFlashAttribute("error", "Atencion: El ID no existe!");
			return "redirect:/views/puntos/";
		}

		puntosService.eliminar(idPunto);
		mensaje.addFlashAttribute("warning", "El registro se elimino con exito!");
		
		return "redirect:/views/puntos/";
	}
	
	@GetMapping("/puntaje")
	public String sumCantidadByUsuarioDni(Model model,  @RequestParam("dni") String dni ) {
		
		
		Integer sumaCantidad = puntosService.sumCantidadByUsuarioDni(dni);
		model.addAttribute("titulo", "Puntaje del Usuario");
		model.addAttribute("puntaje", sumaCantidad);
		
		return "/views/puntos/puntaje";
	}
}
