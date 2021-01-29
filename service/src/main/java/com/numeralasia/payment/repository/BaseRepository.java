package com.numeralasia.payment.repository;

import com.numeralasia.payment.entity.BasicField;
import com.numeralasia.payment.entity.client.Client;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends BasicField> extends JpaRepository<T, Long> {

    List<T> findByActive(Boolean active);
    List<T> findByActive(Boolean active, Sort sort);


}
