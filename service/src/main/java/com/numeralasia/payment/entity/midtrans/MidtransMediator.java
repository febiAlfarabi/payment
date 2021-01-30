package com.numeralasia.payment.entity.midtrans;

import com.numeralasia.payment.component.Spring;
import com.numeralasia.payment.entity.EBase;
import com.numeralasia.payment.util.Constant;
import com.numeralasia.payment.util.ServerUtils;
import io.github.febialfarabi.utility.NumberUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Data
@Table(name="m_midtrans_mediator")
@Entity
@ToString(of = "id")
@EqualsAndHashCode(callSuper = true, of = "id")
public class MidtransMediator extends EBase {

    public static final String TABLE = "m_midtrans_mediator";

    String name = StringUtils.EMPTY;

    @Column(unique = true)
    String paymentType = StringUtils.EMPTY;

    BigDecimal amount = new BigDecimal(0);
    Double percent = Double.valueOf(0);

    String info ;

    String image ;

    @Column(columnDefinition = "BOOLEAN default false")
    Boolean corporateAvailable ;
    @Column(columnDefinition = "BOOLEAN default false")
    Boolean personalAvailable ;

    @Transient
    String imageLink ;

    @Transient
    public BigDecimal amountFee(BigDecimal payment, boolean currencyRounding){
        BigDecimal amountFee = BigDecimal.ZERO;
        if(payment!=null){
            if(percent!=null){
                amountFee = amountFee.add(NumberUtil.amountOfPercent(payment, percent));
                if(currencyRounding){
                    amountFee = NumberUtil.currencyRoundUp(amountFee, BigDecimal.valueOf(100));
                }
            }
        }
        if(amount!=null){
            amountFee = amountFee.add(amount);
        }
        return amountFee ;
    }

    @Override
    public void initTransient() {
        super.initTransient();
        if(StringUtils.isEmpty(imageLink) && StringUtils.isNotEmpty(image)){
            imageLink = Spring.bean(ServerUtils.class).address()+ Constant.REST_MIDTRANS_MEDIATOR_IMAGE+"/"+this.getId()+"/"+this.image ;
        }
    }


}
