package com.numeralasia.payment.entity.client;

import com.numeralasia.payment.entity.EBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name="j_client")
@Entity
@ToString(of = "id")
@EqualsAndHashCode(callSuper = true, of = "id")
public class Client extends EBase {

    String name ;
    String reference ;
    String serviceAddress ;
    String companyClass ;
    String domainClass ;
    String notificationEndpoint ;
}
