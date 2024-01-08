package br.com.sunshine.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_attribute")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAttribute {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	@Column(name = "attribute_name")
	private String attributeName;
	
	@Column(name = "attribute_description")
	private String attributeDescription;
	
	@Column(name = "attribute_price")
	private double attributePrice;
	
	@Column(name = "attribute_category")
	private String attributeCategory;
	
	@Column(name = "number_selection")
	private int numberSelection;
	
	@Column(name = "available")
	private int available;
	
	/* relacionamento */
	
	@JsonIgnore
	@ManyToMany(mappedBy = "productAttributes")
	private List<Amount> amount;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "productAttributes")
	private List<Product> product;
}
