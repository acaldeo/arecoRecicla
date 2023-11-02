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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootclienteapp.SpringbootClienteappApplication;
import com.springbootclienteapp.models.entity.Genero;
import com.springbootclienteapp.models.entity.Rol;
import com.springbootclienteapp.models.entity.Usuario;
import com.springbootclienteapp.models.service.IGeneroService;
import com.springbootclienteapp.models.service.IRolService;
import com.springbootclienteapp.models.service.IUsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/views/usuarios")
public class UserController {
	
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IGeneroService  generoService;
	@Autowired
	private IRolService     rolService;

	private static final Logger logJava = Logger.getLogger(SpringbootClienteappApplication.class);

	@GetMapping({ "/" })
	public String ListarUsuarios(Model model) {

		List<Usuario> listadoUsuarios = usuarioService.Listartodos();
 		
		// Permite Mapear en la vista con thymeleaf por medio del atributo th:
		model.addAttribute("Titulo", "Lista de Usuario");
		model.addAttribute("usuarios", listadoUsuarios);
		logJava.debug("Cargando listado de Usuarios");

		return "/views/usuarios/listar";
	}

	@GetMapping("/create")
	public String crear(Model model) {

		Usuario usuario = new Usuario();
		
		List<Genero> listaGenero= generoService.listaGenero();
		List<Rol> listaRol= rolService.listaRol();
		
		model.addAttribute("titulo", "formulario: Nuevo Usuario");
		model.addAttribute("usuario", usuario);
		model.addAttribute("genero", listaGenero);
		model.addAttribute("rol", listaRol);
		
		return "/views/usuarios/frmCrear";
	}

	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model,
			RedirectAttributes mensaje) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "formulario: Nuevo Usuario");
			model.addAttribute("usuario", usuario);
			return "/views/usuarios/frmCrear";
		}

		usuarioService.guardar(usuario);
		mensaje.addFlashAttribute("success", "Datos guardados con Exito!");

		return "redirect:/views/usuarios/";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idUsuario, Model model, RedirectAttributes mensaje) {

		Usuario usuario = null;
		List<Genero> listaGenero= generoService.listaGenero();
		List<Rol> listaRol= rolService.listaRol();
		if (idUsuario > 0) {

			usuario = usuarioService.buscarPorId(idUsuario);
						

			if (usuario == null) {

				mensaje.addFlashAttribute("error", "Error:El ID no existe!");
				return "redirect:/views/usuarios/";
			}

		} else {

			mensaje.addFlashAttribute("error", "Atencion: El ID no existe!");
			return "redirect:/views/usuarios/";
		}

		model.addAttribute("titulo", "formulario: Editar Usuario");
		model.addAttribute("usuario", usuario);
		model.addAttribute("genero", listaGenero);
		model.addAttribute("rol", listaRol);
		
		return "/views/usuarios/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idUsuario, RedirectAttributes mensaje) {

		Usuario usuario = null;

		if (idUsuario > 0) {

			usuario = usuarioService.buscarPorId(idUsuario);

			if (usuario == null) {

				mensaje.addFlashAttribute("error", "Error:El ID no existe!");
				return "redirect:/views/usuarios/";
			}

		} else {

			mensaje.addFlashAttribute("error", "Atencion: El ID no existe!");
			return "redirect:/views/usuarios/";
		}

		usuarioService.eliminar(idUsuario);
		mensaje.addFlashAttribute("warning", "El registro se elimino con exito!");
		
		return "redirect:/views/usuarios/";
	}
}
