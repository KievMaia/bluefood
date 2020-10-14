package br.com.kiev.bluefood.infrastructure.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.kiev.bluefood.application.service.ClienteService;
import br.com.kiev.bluefood.application.service.RestauranteService;
import br.com.kiev.bluefood.application.service.ValidationException;
import br.com.kiev.bluefood.domain.cliente.Cliente;
import br.com.kiev.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.kiev.bluefood.domain.restaurante.Restaurante;

//Anotação do spring informando que essa classe é um controller
@Controller
//Mapeamento informando que este controller estará acessível para o público pelo /public na barra de endereço
@RequestMapping(path = "/public")
public class PublicController {
	
	//O mesmo que o @Inject do Hibernate.
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	//Pega os atributos dos campos de texto do html
	// O model é o tipo de Objeto recebido pelo html, que deve adicionar o atributo, neste caso Cliente(), 
	//que recebe um nome "cliente" e retorna para a mesma página
	@GetMapping("/cliente/new")
	public String newCliente(Model model) {	
		model.addAttribute("cliente", new Cliente());
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}
	
	@GetMapping("/restaurante/new")
	public String newRestaurante(Model model) {	
		model.addAttribute("restaurante", new Restaurante());
		ControllerHelper.setEditMode(model, false);
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		return "restaurante-cadastro";
	}
	
	//Neste caso agora o post que recebe os dados do html e processa no método save de persist�ncia
	//A anotação @Valid, valida se o Cliente está válido de acordo com com as regras de validação anotadas na classe Cliente.
	@PostMapping(path = "/cliente/save")
	public String saveCliente(@ModelAttribute("cliente") @Valid Cliente cliente, Errors errors, Model model) {
		
		if (!errors.hasErrors()) {
			try {
				clienteService.saveCliente(cliente);
				model.addAttribute("msg", "Cliente gravado com sucesso!");
				
			}catch (ValidationException e) {
				//A chamada deste método faz o encaixe do th:erros do thymelef com a mensagem tratada da exceção.
				errors.rejectValue("email", null, e.getMessage());
			}
			
		}
		
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}
	
	@PostMapping(path = "/restaurante/save")
	public String saveRestaurante(@ModelAttribute("restaurante") @Valid Restaurante restaurante, Errors errors, Model model) {
		
		if (!errors.hasErrors()) {
			try {
				restauranteService.saveRestaurante(restaurante);
				model.addAttribute("msg", "Restaurante gravado com sucesso!");
				
			}catch (ValidationException e) {
				//A chamada deste método faz o encaixe do th:erros do thymelef com a mensagem tratada da exceção.
				errors.rejectValue("email", null, e.getMessage());
			}
			
		}
		
		ControllerHelper.setEditMode(model, false);
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		return "restaurante-cadastro";
	}
}
