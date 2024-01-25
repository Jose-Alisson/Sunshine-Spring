package br.com.sunshine.repository;

import br.com.sunshine.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, String> {

    @Query(value = "select * from addresses where account_id = ?1", nativeQuery = true)
    List<Address> findAllByUserId(String id);
}
