package br.com.sunshine.repository;

import br.com.sunshine.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    @Transactional
    @Modifying
    @Query(value = "update payments set type_pay = ?1 where id = ?2", nativeQuery = true)
    void setTypePay(String type, String id);
}
