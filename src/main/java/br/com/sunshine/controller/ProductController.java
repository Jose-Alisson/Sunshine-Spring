package br.com.sunshine.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sunshine.model.Product;
import br.com.sunshine.repository.ProductRepository;

@RequestMapping("/api/product")
@RestController
public class ProductController{
	
	@Autowired
	private ProductRepository repository;
	
	
	@PostMapping("/save")
	public Product save(@RequestBody Product t) {
		return repository.save(t);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") String id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/findById/{id}")
	public Optional<Product> findById(@PathVariable("id") String id) {
		return repository.findById(id);
	}
	
	@GetMapping("/findAll")
	public List<Product> findAll() {
		return repository.findAll();
	}
}
