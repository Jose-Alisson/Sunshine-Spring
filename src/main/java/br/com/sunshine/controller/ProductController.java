package br.com.sunshine.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.sunshine.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import br.com.sunshine.model.Product;
import br.com.sunshine.repository.ProductRepository;

@RequestMapping("/api/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid Product product){
        return ResponseEntity.ok(service.create(product));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        service.delete(id);
        return ResponseEntity.ok("Successful");
    }

    @GetMapping("/establishment/{id}/{offset}")
    public ResponseEntity<?> getByOffSet(@PathVariable("id") String id, @PathVariable("offset") int number){
        return ResponseEntity.ok(service.getByOffSet(id, number));
    }

    @GetMapping("/size/establishment/{id}")
    public  ResponseEntity<?> getSIze(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getSizeByEstablishment(id));
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
