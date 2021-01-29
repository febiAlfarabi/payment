package com.numeralasia.payment.repository.midtrans;

import com.numeralasia.payment.entity.midtrans.MidtransMediator;
import com.numeralasia.payment.repository.EBaseRepository;

import java.util.Optional;

public interface MidtransMediatorRepositoryE extends EBaseRepository<MidtransMediator> {


    Optional<MidtransMediator> findByPaymentType(String paymentType);

//    List<MidtransMediator> findByActive(Boolean active, Sort sort);


}
