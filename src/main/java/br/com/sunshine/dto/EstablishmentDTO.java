package br.com.sunshine.dto;

import br.com.sunshine.model.Address;
import br.com.sunshine.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class EstablishmentDTO {

    @NotBlank(message = "O nome do estabelecimento não pode ser vazio")
    @NotNull(message = "O nome do estabelecimento não pode ser nulo")
    String name;

    Address address;

    boolean isOpen;

    private List<Product> products;
}
