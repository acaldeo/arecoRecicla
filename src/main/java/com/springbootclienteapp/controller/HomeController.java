//clase que hace el llamado a home.html
package com.springbootclienteapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"/"})
	public String index() {
		return "home";
	}

}
