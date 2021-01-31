package com.numeralasia.payment.model.payment;

import com.numeralasia.payment.model.EBaseDto;
import com.numeralasia.payment.model.client.ClientDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = "id")
@EqualsAndHashCode(callSuper = true, of = "id")
public class TransactionGateDto extends EBaseDto {

    String orderId ;

    ClientDto client ;

}
