package com.numeralasia.payment.service.client;

import com.numeralasia.payment.entity.client.Client;
import com.numeralasia.payment.entity.midtrans.MidtransMediator;
import com.numeralasia.payment.model.exception.AppException;
import com.numeralasia.payment.repository.BaseRepository;
import com.numeralasia.payment.repository.client.ClientRepository;
import com.numeralasia.payment.repository.midtrans.MidtransMediatorRepository;
import com.numeralasia.payment.service.BasicRepoService;
import com.numeralasia.payment.util.Constant;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService extends BasicRepoService<Client> {

    @Autowired private ClientRepository repository ;

    @Override
    public BaseRepository<Client> repository() {
        return repository;
    }

    public Client findByReference(String reference){
        return repository.findByReference(reference).orElseThrow(() -> new AppException(Constant.FAILED_CODE, reference+" : not found"));
    }



}
