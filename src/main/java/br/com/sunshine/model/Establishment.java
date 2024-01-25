package br.com.sunshine.model;


import br.com.sunshine.enums.TypeDelivery;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "establishments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "O nome do estabelecimento não pode ser vazio")
    @NotNull(message = "O nome do estabelecimento não pode ser nulo")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "is_open")
    private boolean isOpen;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "establishment_products", joinColumns = @JoinColumn(name = "establishment_id"),  inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private TypeDelivery typeDelivery;

    @ManyToMany
    @JoinTable(name = "establishment_taxas",  joinColumns = @JoinColumn(name = "establishment_id"), inverseJoinColumns = @JoinColumn(name = "taxa_id"))
    private List<Taxa> taxas;

    @ManyToMany
    @JoinTable(name = "establishment_orders", joinColumns = @JoinColumn(name = "establishment_id"), inverseJoinColumns = @JoinColumn(name = "orders_id"))
    private List<Order> orders;
}
