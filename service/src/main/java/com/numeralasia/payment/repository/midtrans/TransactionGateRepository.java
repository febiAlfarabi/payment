package com.numeralasia.payment.repository.midtrans;

import com.numeralasia.payment.entity.midtrans.TransactionGate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionGateRepository extends JpaRepository<TransactionGate, Long> {

    Optional<TransactionGate> findByOrderId(String orderId);
}
