package com.numeralasia.payment.repository.client;

import com.numeralasia.payment.entity.client.Client;
import com.numeralasia.payment.repository.BaseRepository;

import java.util.Optional;

public interface ClientRepository extends BaseRepository<Client> {

    Optional<Client> findByReference(String reference);


}
