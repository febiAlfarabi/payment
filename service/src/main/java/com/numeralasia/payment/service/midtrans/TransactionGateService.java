package com.numeralasia.payment.service.midtrans;

import com.numeralasia.payment.entity.midtrans.TransactionGate;
import com.numeralasia.payment.model.exception.AppException;
import com.numeralasia.payment.repository.EBaseRepository;
import com.numeralasia.payment.repository.midtrans.TransactionGateRepository;
import com.numeralasia.payment.service.BasicRepoService;
import com.numeralasia.payment.model.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionGateService extends BasicRepoService<TransactionGate> {

    @Autowired private TransactionGateRepository repository ;

    @Override
    public EBaseRepository<TransactionGate> repository() {
        return repository;
    }

    public TransactionGate findByOrderId(String orderId){
        return repository.findByOrderId(orderId).orElseThrow(() -> new AppException(Constant.FAILED_CODE, "Order ID "+orderId+" : not found"));
    }
    public TransactionGate findByPaymentId(String paymentId){
        return repository.findByPaymentId(paymentId).orElseThrow(() -> new AppException(Constant.FAILED_CODE, "Payment ID "+paymentId+" : not found"));
    }



}
