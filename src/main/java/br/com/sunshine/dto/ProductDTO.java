package br.com.sunshine.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {
    String id;
    String photoUrl;
    String productName;
    String description;
    double basePrice;
    String category;
    int available;
}
