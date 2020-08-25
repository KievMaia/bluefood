package br.com.kiev.bluefood.application.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.kiev.bluefood.domain.cliente.Cliente;
import br.com.kiev.bluefood.domain.cliente.ClienteRepository;
import br.com.kiev.bluefood.domain.restaurante.CategoriaRestaurante;
import br.com.kiev.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.kiev.bluefood.domain.restaurante.Restaurante;
import br.com.kiev.bluefood.domain.restaurante.RestauranteRepository;
import br.com.kiev.bluefood.util.StringUtils;

@Component
public class InsertDataForTesting {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		clientes();
		Restaurante[] restaurantes = restaurantes();
	}
	
	private Restaurante[] restaurantes() {
		List<Restaurante> restaurantes = new ArrayList<>();
		
		CategoriaRestaurante categoriaPizza = categoriaRestauranteRepository.findById(1).orElseThrow();
		CategoriaRestaurante categoriaSanduiche = categoriaRestauranteRepository.findById(2).orElseThrow();
		CategoriaRestaurante categoriaSobremesa = categoriaRestauranteRepository.findById(3).orElseThrow();
		CategoriaRestaurante categoriaJapones = categoriaRestauranteRepository.findById(4).orElseThrow();
		
		Restaurante r = new Restaurante();
		r.setNome("Bubger King");
		r.setEmail("r1@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000101");
		r.setTaxaEntrega(BigDecimal.valueOf(3.2));
		r.setTelefone("48999995875");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0001-logo.png");
		r.setTempoEntregaBase(30);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Mc Naldos");
		r.setEmail("r2@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000102");
		r.setTaxaEntrega(BigDecimal.valueOf(4.5));
		r.setTelefone("4899866325");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0002-logo.png");
		r.setTempoEntregaBase(25);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Sbubby");
		r.setEmail("r3@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000103");
		r.setTaxaEntrega(BigDecimal.valueOf(12.2));
		r.setTelefone("48966523254");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0003-logo.png");
		r.setTempoEntregaBase(38);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Pizza Burt");
		r.setEmail("r4@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000104");
		r.setTaxaEntrega(BigDecimal.valueOf(9.8));
		r.setTelefone("48984547854");
		r.getCategorias().add(categoriaPizza);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0004-logo.png");
		r.setTempoEntregaBase(22);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Wiki Japa");
		r.setEmail("r5@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000105");
		r.setTaxaEntrega(BigDecimal.valueOf(14.9));
		r.setTelefone("48984623214");
		r.getCategorias().add(categoriaJapones);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0005-logo.png");
		r.setTempoEntregaBase(19);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		Restaurante[] array = new Restaurante[restaurantes.size()];
		return restaurantes.toArray(array);
		
	}
	
	private Cliente[] clientes() {
		List<Cliente> clientes = new ArrayList<>();
		
		Cliente c = new Cliente();
		c.setNome("Jo�o Silva");
		c.setEmail("joao@bluefood.com.br");
		c.setSenha(StringUtils.encrypt("c"));
		c.setCep("88144752");
		c.setCpf("02014547589");
		c.setTelefone("48966563258");
		clienteRepository.save(c);
		
		c = new Cliente();
		c.setNome("Maria Souza");
		c.setEmail("maria@bluefood.com.br");
		c.setSenha(StringUtils.encrypt("c"));
		c.setCep("88148552");
		c.setCpf("02045847589");
		c.setTelefone("48966533258");
		clienteRepository.save(c);
		
		Cliente[] array = new Cliente[clientes.size()];
		return clientes.toArray(array);
	}
}
