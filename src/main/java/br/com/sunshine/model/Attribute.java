package br.com.sunshine.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
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
public class Attribute {
	
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

	@JsonIgnore
	@ManyToMany(mappedBy = "productAttributes")
	private List<Amount> amount;
	
	@JoinColumn(name = "product_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Product product;
}
