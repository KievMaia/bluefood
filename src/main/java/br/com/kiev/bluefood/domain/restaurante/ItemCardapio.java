package br.com.kiev.bluefood.domain.restaurante;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.kiev.bluefood.infrastructure.web.validator.UploadConstrain;
import br.com.kiev.bluefood.util.FileType;
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
	
	@NotBlank(message = "O nome n�o pode ser vazio")
	@Size(max = 50)
	private String nome;
	
	@NotBlank(message = "A descricao n�o pode ser vazia")
	@Size(max = 80)
	private String descricao;
	
	@NotBlank(message = "A categoria n�o pode ser vazia")
	@Size(max = 25)
	private String categoria;
	
	@Size(max = 50)
	private String imagem;
	
	@NotNull(message = "O pre�o n�o pode ser vazio")
	@Min(0)
	private BigDecimal preco;
	
	@NotNull
	private boolean destaque;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;
	
	@UploadConstrain(acceptedTypes = {FileType.PNG, FileType.JPG}, message = "O arquivo n�o � um arquivo de imagem v�lido" )
	private transient MultipartFile imagemFile;
	
	public void setImagemFileName() {
		if (getId() == null) {
			throw new IllegalStateException("O objeto precisa primeiro ser criado");
		}
		
		this.imagem = String.format("%04d-categoria.%s", getId(), FileType.of(imagemFile.getContentType()).getExtension());
	}
}
