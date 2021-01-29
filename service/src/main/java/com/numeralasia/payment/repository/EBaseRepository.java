package com.numeralasia.payment.repository;

import com.numeralasia.payment.entity.EBase;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EBaseRepository<T extends EBase> extends JpaRepository<T, Long> {

    List<T> findByActive(Boolean active);
    List<T> findByActive(Boolean active, Sort sort);


}
