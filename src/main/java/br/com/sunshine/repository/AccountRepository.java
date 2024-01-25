package br.com.sunshine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sunshine.model.Account;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, String>{
	
	@Query(value = "SELECT * FROM accounts WHERE email = ?1", nativeQuery = true)
	Optional<Account> findByEmail(String email);

	
	@Query(value = "select exists(select 1 from account a where a.email = ?1)", nativeQuery = true)
	long existByEmail(String email);
}
