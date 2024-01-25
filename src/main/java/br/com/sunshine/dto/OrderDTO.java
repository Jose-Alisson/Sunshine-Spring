package br.com.sunshine.dto;

import br.com.sunshine.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    String id;
    List<AmountDTO> amounts;
    PaymentDTO payment;
    OrderStatus status;
    LocalDateTime dateCreation;
    AddressDTO address;
}
