package com.numeralasia.payment.model.midtrans;

import com.numeralasia.payment.model.EBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

@Data
@ToString(of = "id")
@EqualsAndHashCode(callSuper = true, of = "id")
public class MidtransFeeDto extends EBaseDto {

    String name = StringUtils.EMPTY;
    String paymentType = StringUtils.EMPTY;
    BigDecimal amount = new BigDecimal(0);
    Double percent = Double.valueOf(0);

}
