package br.com.kiev.bluefood.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	//Mapeamento para abrir a tela de login.
	
	@GetMapping(path = {"/login", "/"})
	public String login() {
		return "login";
	}
}