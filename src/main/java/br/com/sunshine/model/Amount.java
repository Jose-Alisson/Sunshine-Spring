package br.com.sunshine.model;

import java.util.List;

import br.com.sunshine.enums.AmountStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "amounts")
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
	
	@Enumerated(EnumType.STRING)
	private AmountStatus status;

	private int quantity;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "amount_product_attribute", joinColumns = @JoinColumn(name = "amount_id"), inverseJoinColumns = @JoinColumn(name = "attribute_id"))
	private List<ProductAttribute> productAttributes;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;
}
