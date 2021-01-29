package com.numeralasia.payment.service.midtrans;

import com.numeralasia.payment.entity.midtrans.MidtransMediator;
import com.numeralasia.payment.repository.BaseRepository;
import com.numeralasia.payment.repository.midtrans.MidtransMediatorRepository;
import com.numeralasia.payment.service.BasicRepoService;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class MidtransMediatorService extends BasicRepoService<MidtransMediator> {

    @Autowired private MidtransMediatorRepository repository ;

    @Override
    public BaseRepository<MidtransMediator> repository() {
        return repository;
    }

    public boolean existById(Long id){
        return repository.existsById(id);
    }

    public List<MidtransMediator> findAll(Boolean active, String sortir, Boolean ascending) {
        Sort sort = new Sort(ascending?Sort.Direction.ASC: Sort.Direction.DESC, sortir);
        List<MidtransMediator> banks = new ArrayList<>();
        if(active!=null){
            banks = repository.findByActive(active, sort);
        }else{
            banks = repository.findAll(sort);
        }
        return banks;
    }

    public List<MidtransMediator> findAll(Boolean corporateAvailable, Boolean personalAvailable, Boolean active, String sortir, Boolean ascending) {
        Sort sort = new Sort(ascending?Sort.Direction.ASC: Sort.Direction.DESC, sortir);
        List<MidtransMediator> banks = new ArrayList<>();
        String sql = "SELECT mm FROM MidtransMediator mm WHERE 1+1 = 2 ";
        if(BooleanUtils.isTrue(corporateAvailable)){
            sql = sql+" AND mm.corporateAvailable = :corporateAvailable ";
        }
        if(BooleanUtils.isTrue(personalAvailable)){
            sql = sql+" AND mm.personalAvailable = :personalAvailable ";
        }
        if(active!=null){
            sql = sql+" AND mm.active = :active ";
        }
        sql = sql+" ORDER BY "+sortir+(ascending?" ASC ":" DESC ");
        Query query = entityManager.createQuery(sql);
        if(corporateAvailable!=null && corporateAvailable){
            query.setParameter("corporateAvailable", corporateAvailable);
        }
        if(personalAvailable!=null && personalAvailable){
            query.setParameter("personalAvailable", personalAvailable);
        }
        if(active!=null){
            query.setParameter("active", active);
        }
        banks = query.getResultList();

        return banks;
    }



    public MidtransMediator findByPaymentType(String paymentType){
        return repository.findByPaymentType(paymentType).orElse(new MidtransMediator());
    }


}
