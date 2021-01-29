package com.numeralasia.payment.repository.midtrans;

import com.numeralasia.payment.entity.midtrans.TransactionGate;
import com.numeralasia.payment.repository.EBaseRepository;

import java.util.Optional;

public interface TransactionGateRepository extends EBaseRepository<TransactionGate> {

    Optional<TransactionGate> findByOrderId(String orderId);
}
