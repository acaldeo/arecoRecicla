package com.springbootclienteapp.models.service;

import java.util.List;

import com.springbootclienteapp.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> Listartodos();
	public void guardar(Usuario usuario);
	public Usuario buscarPorId( Long id);
	public void eliminar (Long id);
	
}
