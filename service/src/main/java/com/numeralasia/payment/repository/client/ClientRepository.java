package com.numeralasia.payment.repository.client;

import com.numeralasia.payment.entity.client.Client;
import com.numeralasia.payment.repository.EBaseRepository;

import java.util.Optional;

public interface ClientRepository extends EBaseRepository<Client> {

    Optional<Client> findByReference(String reference);


}
