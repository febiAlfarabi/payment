package com.numeralasia.payment.repository.midtrans;

import com.numeralasia.payment.entity.midtrans.MidtransMediator;
import com.numeralasia.payment.repository.BaseRepository;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface MidtransMediatorRepository extends BaseRepository<MidtransMediator> {


    Optional<MidtransMediator> findByPaymentType(String paymentType);

//    List<MidtransMediator> findByActive(Boolean active, Sort sort);


}
