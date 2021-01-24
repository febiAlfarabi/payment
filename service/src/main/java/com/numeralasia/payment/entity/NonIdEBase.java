package com.numeralasia.payment.entity;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;

@MappedSuperclass
@DynamicUpdate
@DynamicInsert
public class NonIdEBase extends BasicField implements Serializable {


    @Id
    Long id ;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id ;
    }

    @PreUpdate
    @PrePersist
    public void prePersist(){
        super.prePersist();
    }

}
