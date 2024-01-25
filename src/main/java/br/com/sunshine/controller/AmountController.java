package br.com.sunshine.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sunshine.dto.AmountDTO;
import br.com.sunshine.services.AmountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import br.com.sunshine.model.Amount;
import br.com.sunshine.repository.AmountRepository;

@RestController
@RequestMapping("/api/amount")
public class AmountController {


    @Autowired
    private AmountService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid Amount amount){
        return ResponseEntity.ok(service.create(amount));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        service.delete(id);
        return ResponseEntity.ok("Successful");
    }

    @GetMapping("/byAccountId/{id}")
    public ResponseEntity<?> byAccountId(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getAllByUserId(id));
    }

    @PatchMapping("/decrement/{id}")
    public ResponseEntity<?> decrement(@PathVariable("id") String id){
        service.decrement(id);
        return ResponseEntity.ok("Decremented");
    }

    @PatchMapping("/increment/{id}")
    public ResponseEntity<?> increment(@PathVariable("id") String id){
        service.increment(id);
        return ResponseEntity.ok("Incremented");
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(BindException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        List<FieldError> errors = ex.getFieldErrors();

        for (FieldError error : errors) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            errorsMap.put(field, message);
        }

        return errorsMap;
    }
}
