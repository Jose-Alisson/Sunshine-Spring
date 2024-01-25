package br.com.sunshine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "taxas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Taxa {

    @Id
    private String id;

    @Column(name = "distance")
    private int distance;

    @Column(name = "price")
    private double price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "establishment_id", nullable = false)
    private Establishment establishment;
}
