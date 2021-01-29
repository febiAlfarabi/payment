package com.numeralasia.payment.service.midtrans;

import com.numeralasia.payment.entity.midtrans.TransactionGate;
import com.numeralasia.payment.model.exception.AppException;
import com.numeralasia.payment.repository.EBaseRepository;
import com.numeralasia.payment.repository.midtrans.TransactionGateRepositoryE;
import com.numeralasia.payment.service.BasicRepoService;
import com.numeralasia.payment.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionGateService extends BasicRepoService<TransactionGate> {

    @Autowired private TransactionGateRepositoryE repository ;

    @Override
    public EBaseRepository<TransactionGate> repository() {
        return repository;
    }

    public TransactionGate findByOrderId(String orderId){
        return repository.findByOrderId(orderId).orElseThrow(() -> new AppException(Constant.FAILED_CODE, orderId+" : not found"));
    }



}
