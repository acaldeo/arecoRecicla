package com.springbootclienteapp.models.service;
/*Clase que implementa todos los metodos declarados en la clase servicio.*/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootclienteapp.models.entity.Rol;
import com.springbootclienteapp.models.repository.RolRepository;

@Service
public class RolServiceImplement implements IRolService {
	
	@Autowired
	private RolRepository  rolRepository;

	@Override
	public List<Rol> listaRol() {
		// TODO Auto-generated method stub
		return (List<Rol>) rolRepository.findAll();
	}

}
