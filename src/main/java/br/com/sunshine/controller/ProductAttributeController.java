package br.com.sunshine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sunshine.model.ProductAttribute;
import br.com.sunshine.repository.AttributeRepository;

@RequestMapping("/api/product/attribute")
@RestController
public class ProductAttributeController {
	
	@Autowired
	private AttributeRepository repository;
	
	@PostMapping("/save")
	public ProductAttribute save(@RequestBody() ProductAttribute t) {
		return repository.save(t);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") String id) {
		repository.rrp(id);
		repository.deleteById(id);
	}
	
	@GetMapping("/findAllByProductId/{id}")
	public List<ProductAttribute> findByProductId(@PathVariable("id") String id){
		return this.repository.findAllByProductId(id);
	}
}
