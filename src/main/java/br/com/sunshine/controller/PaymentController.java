package br.com.sunshine.controller;

import br.com.sunshine.dto.PaymentDTO;
import br.com.sunshine.model.Payment;
import br.com.sunshine.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/create")
    public ResponseEntity<PaymentDTO> create(@RequestBody @Valid Payment payment){
        return ResponseEntity.ok(service.create(payment));
    }
}
