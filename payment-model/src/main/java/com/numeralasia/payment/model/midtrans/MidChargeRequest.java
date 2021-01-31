//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.numeralasia.payment.model.midtrans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class MidChargeRequest implements Serializable {

    @JsonProperty("transaction_details")
    @SerializedName("transaction_details")
    MidTransactionDetails transactionDetails ;
    @JsonProperty("customer_details")
    @SerializedName("customer_details")
    MidCustomerDetails customerDetails ;
    @JsonProperty("item_details")
    @SerializedName("item_details")
    List<MidTransactionItem> itemDetails = new ArrayList<>();

    @JsonProperty("custom_expiry")
    @SerializedName("custom_expiry")
    CustomExpiry customExpiry ;

    @JsonProperty("credit_card")
    @SerializedName("credit_card")
    CreditCard creditCard = new CreditCard();

    @JsonProperty("enabled_payments")
    @SerializedName("enabled_payments")
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
