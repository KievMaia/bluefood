package br.com.kiev.bluefood.domain.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.kiev.bluefood.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//Anotação Lombok que cria automaticamente os getters and setters e também o equals e hascode.
@Getter
@Setter
//onlyExplicitlyInclud = true, indica que você escolhe qual parâmetros será aplicado o equal e hashcode.
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//Esta marcação indica a JPA que apesar desta classe não ser uma entidade ela faz parte de uma em que a estendeu.
@MappedSuperclass
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Indica que apenas no id que está sendo incluído o equals e hashcode.
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank(message = "O nome não pode ser vazio")
	@Size(max = 80, message = "O nome é muito grande")
	private String nome;
	
	@NotBlank(message = "O e-mail não pode ser vazio")
	@Size(max = 60, message = "O e-mail é muito grande")
	@Email(message = "O e-mail é inválido")
	private String email;
	
	@NotBlank(message = "A senha não pode ser vazia")
	@Size(max = 80, message = "A senha é muito grande")
	private String senha;
	
	@NotBlank(message = "O telefone não pode ser vazio")
	@Pattern(regexp = "[0-9]{10,11}", message = "O telefone possui formato inválido")
	@Column(length = 11, nullable = false)
	private String telefone;
	
	public void encryptPassword() {
		this.senha = StringUtils.encrypt(this.senha);
	}
}
