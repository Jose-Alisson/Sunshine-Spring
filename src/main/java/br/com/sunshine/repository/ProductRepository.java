package br.com.sunshine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sunshine.model.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "SELECT COUNT(*) AS total FROM products where establishment_id = ?1", nativeQuery = true)
    int size(String id);

    @Query(value = "SELECT * FROM products WHERE establishment_id = ?1 LIMIT 12 OFFSET ?2", nativeQuery = true)
    List<Product> getByOffSet(String establishmentId, int offset);
}
