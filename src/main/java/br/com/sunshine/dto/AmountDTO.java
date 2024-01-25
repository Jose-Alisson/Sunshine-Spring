package br.com.sunshine.dto;

import br.com.sunshine.model.Product;
import br.com.sunshine.model.ProductAttribute;
import lombok.Data;

import java.util.List;

@Data
public class AmountDTO {

    String id;
    ProductDTO product;
    int quantity;
    List<ProductAttribute> productAttributes;
}
