package com.numeralasia.payment.model.midtrans;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreditCard implements Serializable {
    boolean secure = true ;
}
