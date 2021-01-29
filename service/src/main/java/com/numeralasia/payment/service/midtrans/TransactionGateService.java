package com.numeralasia.payment.service.midtrans;

import com.numeralasia.payment.entity.midtrans.TransactionGate;
import com.numeralasia.payment.model.exception.AppException;
import com.numeralasia.payment.repository.BaseRepository;
import com.numeralasia.payment.repository.midtrans.TransactionGateRepository;
import com.numeralasia.payment.service.BasicRepoService;
import com.numeralasia.payment.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionGateService extends BasicRepoService<TransactionGate> {

    @Autowired private TransactionGateRepository repository ;

    @Override
    public BaseRepository<TransactionGate> repository() {
        return repository;
    }

    public TransactionGate findByOrderId(String orderId){
        return repository.findByOrderId(orderId).orElseThrow(() -> new AppException(Constant.FAILED_CODE, orderId+" : not found"));
    }



}
