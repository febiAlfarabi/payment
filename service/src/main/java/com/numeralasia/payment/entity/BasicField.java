package com.numeralasia.payment.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.numeralasia.payment.util.Constant;
import com.numeralasia.payment.util.JsonDateTimeDeserializer;
import com.numeralasia.payment.util.JsonDateTimeSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate(value = false)
@Data
public abstract class BasicField {

    public abstract Long getId();
    public abstract void setId(Long id);


    //, nullable = false, columnDefinition = "DEFAULT CURRENT_TIMESTAMP"
    @Column(name="created")
    @Getter@Setter
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    Date created ;

    @Column(name="created_by")
    Long createdBy ;

    //, nullable = false, columnDefinition = "DEFAULT CURRENT_TIMESTAMP"
    @Column(name="updated")
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    Date updated ;

    @Column(name="updated_by")
    Long updatedBy ;

    @Column(name="active")
    Boolean active ;

    @PreUpdate
    @PrePersist
    public void prePersist(){
        initTransient();
    }

    @PostPersist
    @PostUpdate
    public void postPersist() {
        initTransient();
    }

    @PostLoad
    public void postLoad(){
        initTransient();
    }

    public void initTransient(){
        if(createdBy==null || createdBy.equals(0l)){
            createdBy = Constant.SYSTEM_REGISTER;
        }
        if(updatedBy==null || updatedBy.equals(0l)){
            updatedBy = Constant.SYSTEM_REGISTER;
        }
        if(created==null){
            created = new Date();
        }
        if(updated==null){
            updated = new Date();
        }
//        updated = new Date();
        if(active==null){
            active = Boolean.TRUE;
        }
    }


}
