package com.springbootclienteapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springbootclienteapp.models.entity.Usuario;


@SpringBootApplication
public class SpringbootClienteappApplication {
		
	public static void main(String[] args){
		SpringApplication.run(SpringbootClienteappApplication.class, args);
	
		Usuario usuario = new Usuario();
		
		System.out.println(usuario.getRoles());
	}

}
