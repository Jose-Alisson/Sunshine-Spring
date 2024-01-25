package br.com.sunshine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.sunshine.model.Amount;
import org.springframework.transaction.annotation.Transactional;

public interface AmountRepository extends JpaRepository<Amount, String>{
	
	@Query(value = "select * from amount where account_id = ?1 and status = 'DESANEXADO'", nativeQuery = true)
	List<Amount> findAllByUserId(String id);

	@Modifying
	@Transactional
	@Query(value = "update amount set status = 'ANEXADO' where id in ?1", nativeQuery = true)
	void attachAll(List<String> ids);

	@Modifying
	@Transactional
	@Query(value = "update amount set quantity = quantity - ?2 where id in ?1", nativeQuery = true)
	void decrement(String id, int value);
}
