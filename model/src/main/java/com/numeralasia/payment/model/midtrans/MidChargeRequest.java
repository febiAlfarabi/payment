//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.numeralasia.payment.model.midtrans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MidChargeRequest {

    @JsonProperty("transaction_details")
    MidTransactionDetails transactionDetails ;
    @JsonProperty("customer_details")
    MidCustomerDetails customerDetails ;
    @JsonProperty("item_details")
    List<MidTransactionItem> itemDetails = new ArrayList<>();

    @JsonProperty("custom_expiry")
    CustomExpiry customExpiry ;

    @JsonProperty("credit_card")
    CreditCard creditCard = new CreditCard();

    @JsonProperty("enabled_payments")
    List<String> enabledPayments = new ArrayList<>();

    public MidChargeRequest() {
    }

    public MidChargeRequest(MidTransactionDetails transactionDetails, MidCustomerDetails customerDetails) {
        this.transactionDetails = transactionDetails ;
        this.customerDetails = customerDetails ;
    }

    public MidChargeRequest(MidTransactionDetails transactionDetails, List<MidTransactionItem> itemDetails, MidCustomerDetails customerDetails) {
        this.transactionDetails = transactionDetails ;
        this.customerDetails = customerDetails ;
        this.itemDetails = itemDetails ;
    }
}
