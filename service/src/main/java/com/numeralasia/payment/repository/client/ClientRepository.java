package com.numeralasia.payment.repository.client;

import com.numeralasia.payment.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByReference(String reference);


}
