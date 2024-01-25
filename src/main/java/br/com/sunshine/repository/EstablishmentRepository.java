package br.com.sunshine.repository;

import br.com.sunshine.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EstablishmentRepository extends JpaRepository<Establishment, String> {


    @Query(value = "SELECT * FROM establishments where name = ?1", nativeQuery = true)
    Optional<Establishment> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE establishments SET = ?2 WHERE id = ?1", nativeQuery = true)
    void setOpen(String id, boolean isOpen);

}
