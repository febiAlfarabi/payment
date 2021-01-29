package com.numeralasia.payment.repository;

import com.numeralasia.payment.entity.NonIdEBase;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface NonIdEBaseRepository<T extends NonIdEBase> extends JpaRepository<T, Long> {

    List<T> findByActive(Boolean active);
    List<T> findByActive(Boolean active, Sort sort);


}
