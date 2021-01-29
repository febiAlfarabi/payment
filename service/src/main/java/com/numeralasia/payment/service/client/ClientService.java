package com.numeralasia.payment.service.client;

import com.numeralasia.payment.entity.client.Client;
import com.numeralasia.payment.model.exception.AppException;
import com.numeralasia.payment.repository.EBaseRepository;
import com.numeralasia.payment.repository.client.ClientRepository;
import com.numeralasia.payment.service.BasicRepoService;
import com.numeralasia.payment.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends BasicRepoService<Client> {

    @Autowired private ClientRepository repository ;

    @Override
    public EBaseRepository<Client> repository() {
        return repository;
    }

    public Client findByReference(String reference){
        return repository.findByReference(reference).orElseThrow(() -> new AppException(Constant.FAILED_CODE, reference+" : not found"));
    }



}
