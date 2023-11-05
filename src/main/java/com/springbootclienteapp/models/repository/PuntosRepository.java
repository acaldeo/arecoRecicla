package com.springbootclienteapp.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springbootclienteapp.models.entity.Usuario;
import com.springbootclienteapp.models.entity.Punto;

public interface PuntosRepository extends CrudRepository<Punto, Long> {
	
	Usuario usuario = new Usuario();
	
	@Query(value="SELECT sum(cantidad) FROM `punto` inner join usuario WHERE punto.usuario_id = usuario.id and usuario.dni = :dni", nativeQuery=true)
	 Integer sumCantidadByUsuarioDni(@Param("dni") String dni);

}
