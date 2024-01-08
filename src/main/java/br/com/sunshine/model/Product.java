package br.com.sunshine.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	@Column(name = "product_name")
	private String productName;
	
	@Size(max = 255, message = "A descrição simples não pode ter mais que 255 caracteres")
	@Column(name = "simple_description")
	private String description;
	
	@Column(name = "all_description", length = 2000)
	private String allDescription;
	
	@Column(name = "base_price")
	private double basePrice;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "available")
	private int available;
	
	//@JoinColumn(name = "product_attribute_id")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "product_product_attribute", 
		joinColumns = @JoinColumn(name = "product_id"), 
		inverseJoinColumns = @JoinColumn(name = "attribute_id")
	)
	private List<ProductAttribute> productAttributes;
	
}
