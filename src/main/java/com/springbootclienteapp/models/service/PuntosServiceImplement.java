package com.springbootclienteapp.models.service;
/*Clase que implementa todos los metodos declarados en la clase servicio.*/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootclienteapp.models.entity.Punto;
import com.springbootclienteapp.models.entity.Usuario;
import com.springbootclienteapp.models.repository.PuntosRepository;

@Service
public class PuntosServiceImplement implements IPuntosService {
	
	@Autowired
	private PuntosRepository  puntosRepository;

	@Override
	public List<Punto> Listartodos() {
		// TODO Auto-generated method stub
		return (List<Punto>) puntosRepository.findAll();
	}

	@Override
	public void guardar(Punto puntos) {
		puntosRepository.save(puntos);
		
	}

	@Override
	public Punto buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return puntosRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		puntosRepository.deleteById(id);
		
	}

	@Override
	public Integer sumCantidadByUsuarioDni(String dni) {
		// TODO Auto-generated method stub
		return puntosRepository.sumCantidadByUsuarioDni(dni);
	}



}
