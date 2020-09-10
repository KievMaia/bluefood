package br.com.kiev.bluefood.domain.pedido;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ItemPedidoPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne
	private Pedido pedido;
	
	@NotNull
	private Integer ordem;
}
