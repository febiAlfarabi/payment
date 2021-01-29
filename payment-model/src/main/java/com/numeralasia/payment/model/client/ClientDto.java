package com.numeralasia.payment.model.client;

import lombok.Data;

@Data
public class ClientDto {

    String name ;
    String reference ;
    String serviceAddress ;
    String companyClass ;
    String domainClass ;
    String notificationEndpoint ;

}
