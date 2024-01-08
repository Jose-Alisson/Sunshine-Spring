package br.com.sunshine.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "amount")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Amount {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "amount_product_attribute", 
		joinColumns = @JoinColumn(name = "amount_id"), 
		inverseJoinColumns = @JoinColumn(name = "attribute_id")
	)
	private List<ProductAttribute> productAttributes;
}
