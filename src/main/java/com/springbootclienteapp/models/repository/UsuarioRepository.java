package com.springbootclienteapp.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springbootclienteapp.models.entity.Usuario;



public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
	