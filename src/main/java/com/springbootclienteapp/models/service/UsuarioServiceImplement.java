package com.springbootclienteapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springbootclienteapp.models.entity.Usuario;
import com.springbootclienteapp.models.repository.UsuarioRepository;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	public List<Usuario> Listartodos() {
		
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public void guardar(Usuario usuario){
		usuarioRepository.save(usuario);

	}

	@Override
	public Usuario buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(id);
	}

}
