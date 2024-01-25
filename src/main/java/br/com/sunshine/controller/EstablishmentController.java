package br.com.sunshine.controller;

import br.com.sunshine.dto.EstablishmentDTO;
import br.com.sunshine.model.Establishment;
import br.com.sunshine.services.EstablishmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/establishment")
public class EstablishmentController {

    @Autowired
    private EstablishmentService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid Establishment establishment){
        return ResponseEntity.ok(service.create(establishment));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid EstablishmentDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        service.delete(id);
        return ResponseEntity.ok("Successful");
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name){
        return ResponseEntity.ok(service.getByName(name));
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
