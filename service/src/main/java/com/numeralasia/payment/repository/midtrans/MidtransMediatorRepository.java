package com.numeralasia.payment.repository.midtrans;

import com.numeralasia.payment.entity.midtrans.MidtransMediator;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MidtransMediatorRepository extends JpaRepository<MidtransMediator, Long> {


    Optional<MidtransMediator> findByPaymentType(String paymentType);

    List<MidtransMediator> findByActive(Boolean active, Sort sort);


}
