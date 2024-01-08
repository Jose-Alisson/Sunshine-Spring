package br.com.sunshine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sunshine.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
