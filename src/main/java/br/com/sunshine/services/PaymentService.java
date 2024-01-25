package br.com.sunshine.services;

import br.com.sunshine.dto.PaymentDTO;
import br.com.sunshine.enums.PaymentMethod;
import br.com.sunshine.enums.PaymentStatus;
import br.com.sunshine.model.Payment;
import br.com.sunshine.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public PaymentDTO create(Payment payment){
        payment.setDateCreation(LocalDateTime.now());
        payment.setStatus(PaymentStatus.NAO_PAGO);
        return mapper.map(repository.save(payment), PaymentDTO.class);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public PaymentDTO getById(String id){
        Optional<Payment> payment = repository.findById(id);
        return payment.map(value -> mapper.map(value, PaymentDTO.class)).orElse(null);
    }
}
