package br.com.kiev.bluefood.infrastructure.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import br.com.kiev.bluefood.domain.cliente.ClienteRepository;
import br.com.kiev.bluefood.domain.restaurante.CategoriaRestaurante;
import br.com.kiev.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.kiev.bluefood.domain.restaurante.Restaurante;
import br.com.kiev.bluefood.domain.restaurante.SearchFilter;
import br.com.kiev.bluefood.util.SecurityUtils;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private RestauranteService restauranteService;

	@GetMapping(path = "/home")
	public String home(Model model) {
		
		List<CategoriaRestaurante> categorias = categoriaRestauranteRepository.findAll(Sort.by("nome"));
		model.addAttribute("categorias", categorias);
		model.addAttribute("searchFilter", new SearchFilter());
		
		return "cliente-home";
	}

	@GetMapping("/edit")
	public String edit(Model model) {
		Integer clienteId = SecurityUtils.loggedCliente().getId();
		Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();
		model.addAttribute("cliente", cliente);
		ControllerHelper.setEditMode(model, true);

		return "cliente-cadastro";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("cliente") @Valid Cliente cliente, Errors errors, Model model) {

		if (!errors.hasErrors()) {
			try {
				clienteService.saveCliente(cliente);
				model.addAttribute("msg", "Cliente alterado com sucesso!");

			} catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());
			}
		}

		ControllerHelper.setEditMode(model, true);
		return "cliente-cadastro";
	}

	@GetMapping(path = "/search")
	public String search(@ModelAttribute("searchFilter") SearchFilter filter, Model model) {
		List<Restaurante> restaurantes = restauranteService.search(filter);
		model.addAttribute("restaurantes", restaurantes);
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		return "cliente-busca";
	}
}