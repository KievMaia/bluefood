package br.com.kiev.bluefood.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	//Mapeamento para abrir a tela de login.
	
	@GetMapping(path = {"/login", "/"})
	public String login() {
		return "login";
	}
	
	@GetMapping(path = "/login-error")
	public String loginError(Model model) {
		model.addAttribute("msg", "Credenciais inválidas!");
		return "login";
	}
}