package br.com.sunshine.dto;

import br.com.sunshine.enums.PaymentStatus;
import br.com.sunshine.enums.PaymentMethod;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    String id;
    LocalDateTime dateCreation;
    PaymentStatus status;
    PaymentMethod paymentMethod;
    double paymentValue;
    double paymentAmount;
}
