package br.com.sunshine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sunshine.model.Amount;

public interface AmountRepository extends JpaRepository<Amount, String>{

}
