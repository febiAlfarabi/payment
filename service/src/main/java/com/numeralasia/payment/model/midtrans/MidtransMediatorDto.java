package com.numeralasia.payment.model.midtrans;

import com.numeralasia.payment.model.EBaseDto;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

@Data
@ToString(of = "id")
public class MidtransMediatorDto extends EBaseDto {

    String label ;
    String name = StringUtils.EMPTY;

    String paymentType = StringUtils.EMPTY;

    BigDecimal amount = new BigDecimal(0);
    Double percent = Double.valueOf(0);

    String info ;

    String image ;

    boolean corporateAvailable ;
    boolean personalAvailable ;

    String imageLink ;



}
