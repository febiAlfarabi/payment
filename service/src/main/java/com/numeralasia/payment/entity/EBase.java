package com.numeralasia.payment.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@EqualsAndHashCode(of = "id")
public class EBase extends BasicField implements Serializable {
    public static final Logger logger = LoggerFactory.getLogger(EBase.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id ;

    @Override
    public Long getId() {
        if (id == null || id.equals(0) || id == 0) {
            this.id = null;
        }
        return id;
    }

    @Override
    public void setId(Long id) {
        if (id == null || id.equals(0) || id == 0) {
            id = null;
        }
        this.id = id;
    }



}
