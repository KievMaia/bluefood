package br.com.kiev.bluefood.domain.restaurante;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_cardapio")
public class ItemCardapio implements Serializable{

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O nome não pode ser vazio")
	@Size(max = 50)
	private String nome;
	
	@NotBlank(message = "A descricao não pode ser vazia")
	@Size(max = 80)
	private String descricao;
	
	@NotBlank(message = "A categoria não pode ser vazia")
	@Size(max = 25)
	private String categoria;
	
	@Size(max = 50)
	private String imagem;
	
	private BigDecimal preco;
	
	private boolean destaque;
	
	@ManyToOne
	private Restaurante restaurante;
}
