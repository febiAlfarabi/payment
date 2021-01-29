package com.numeralasia.payment.repository.midtrans;

import com.numeralasia.payment.entity.midtrans.TransactionGate;
import com.numeralasia.payment.repository.BaseRepository;

import java.util.Optional;

public interface TransactionGateRepository extends BaseRepository<TransactionGate> {

    Optional<TransactionGate> findByOrderId(String orderId);
}
