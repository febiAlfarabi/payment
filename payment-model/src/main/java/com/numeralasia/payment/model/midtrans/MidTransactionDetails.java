//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.numeralasia.payment.model.midtrans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class MidTransactionDetails implements Serializable {
    @JsonProperty("order_id")
    @SerializedName("order_id")
    String orderId;
    @JsonProperty("gross_amount")
    @SerializedName("gross_amount")
    Long grossAmount;

    public MidTransactionDetails() {
    }

    public MidTransactionDetails(String orderId, Long grossAmount) {
        this.orderId = orderId;
        this.grossAmount = grossAmount;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            MidTransactionDetails that = (MidTransactionDetails)o;
            if (this.grossAmount != null) {
                if (!this.grossAmount.equals(that.grossAmount)) {
                    return false;
                }
            } else if (that.grossAmount != null) {
                return false;
            }

            if (this.orderId != null) {
                if (this.orderId.equals(that.orderId)) {
                    return true;
                }
            } else if (that.orderId == null) {
                return true;
            }

            return false;
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.orderId != null ? this.orderId.hashCode() : 0;
        result = 31 * result + (this.grossAmount != null ? this.grossAmount.hashCode() : 0);
        return result;
    }
}
