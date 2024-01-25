package br.com.sunshine.model;

import java.time.LocalDateTime;
import java.util.List;

import br.com.sunshine.enums.OrderStatus;
import br.com.sunshine.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "order_amounts", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "amount_id"))
	private List<Amount> amounts;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "date_creation")
	private LocalDateTime dateCreation;

	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private Address address;

	@JoinColumn(name = "establishment_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Establishment establishment;
}
