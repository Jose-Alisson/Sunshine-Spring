package br.com.sunshine.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.sunshine.dto.OrderDTO;
import br.com.sunshine.enums.OrderStatus;
import br.com.sunshine.model.Payment;
import br.com.sunshine.repository.AmountRepository;
import br.com.sunshine.repository.PaymentRepository;
import br.com.sunshine.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.sunshine.model.Order;
import br.com.sunshine.repository.OrderRepository;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> create(@RequestBody @Valid Order order){
        return ResponseEntity.ok(service.create(order));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id){
        service.delete(id);
        return ResponseEntity.ok("Successful");
    }

    @GetMapping("/byUserId/{id}")
    public ResponseEntity<List<OrderDTO>> getAllByUserId(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getAllByUserId(id));
    }
}
