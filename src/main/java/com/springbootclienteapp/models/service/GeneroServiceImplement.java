package com.springbootclienteapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootclienteapp.models.entity.Genero;
import com.springbootclienteapp.models.repository.GeneroRepository;

@Service
public class GeneroServiceImplement implements IGeneroService {
	
	@Autowired
	private GeneroRepository  generoRepository;

	@Override
	public List<Genero> listaGenero() {
		// TODO Auto-generated method stub
		return (List<Genero>) generoRepository.findAll();
	}

}
