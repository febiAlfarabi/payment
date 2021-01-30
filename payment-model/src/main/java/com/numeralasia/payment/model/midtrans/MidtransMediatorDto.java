package com.numeralasia.payment.model.midtrans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.numeralasia.payment.model.EBaseDto;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

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


    @JsonIgnore
    public BigDecimal amountFee(BigDecimal payment, boolean currencyRounding){
        BigDecimal amountFee = BigDecimal.ZERO;
        if(payment!=null){
            if(percent!=null){
                amountFee = amountFee.add(payment.multiply(BigDecimal.valueOf(percent).divide(BigDecimal.valueOf(100))));
                if(currencyRounding){
                    amountFee = amountFee.setScale(0, RoundingMode.HALF_UP);
                    amountFee = amount.divide(BigDecimal.valueOf(100));
                    amountFee = amountFee.setScale(0, RoundingMode.HALF_UP);
                    amountFee = amount.subtract(BigDecimal.valueOf(100));
                }
            }
        }
        if(amount!=null){
            amountFee = amountFee.add(amount);
        }
        return amountFee ;
    }



}
