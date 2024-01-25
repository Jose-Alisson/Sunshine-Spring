package br.com.sunshine.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDTO {
    private String street;
    private String houseNumber;
    private String complement;
    private String pointReference;
    private String zipCode;
    private String log;
    private String lat;
}
