package com.springbootclienteapp.models.service;

import java.util.List;

import com.springbootclienteapp.models.entity.Punto;

public interface IPuntosService {
	
	public List<Punto> Listartodos();
	public void guardar(Punto puntos);
	public Punto buscarPorId( Long id);
	public void eliminar (Long id);
	public Integer sumCantidadByUsuarioDni(String dni);

}
