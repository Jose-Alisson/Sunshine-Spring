package br.com.sunshine.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name = "photo_url")
	private String photoUrl;

	@NotBlank(message = "O nome do produto não pode ser vazio")
	@NotNull(message = "O nome do produto não pode ser nulo")
	@Column(name = "product_name")
	private String productName;
	
	@Size(max = 255, message = "A descrição simples não pode ter mais que 255 caracteres")
	@Column(name = "simple_description")
	private String description;

	@Size(max = 2000, message = "A Descrição completa não pode ter mais que 2000 caracteres")
	@Column(name = "all_description", length = 2000)
	private String allDescription;
	
	@Column(name = "base_price")
	private double basePrice = 0;

	@NotBlank(message = "A categoria do produto não pode ser vazia")
	@NotNull(message = "A categoria do produto não pode ser nula")
	private String category;

	private int available;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "product_product_attribute", 
		joinColumns = @JoinColumn(name = "product_id"), 
		inverseJoinColumns = @JoinColumn(name = "attribute_id")
	)
	private List<ProductAttribute> attributes;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "establishment_id", nullable = false)
	private Establishment establishment;
}
