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
public class MidCustomerDetails implements Serializable {
    @JsonProperty("first_name")
    @SerializedName("first_name")
    String firstName;
    @JsonProperty("last_name")
    @SerializedName("last_name")
    String lastName;
    String email;
    String phone;
    @JsonProperty("billing_address")
    @SerializedName("billing_address")
    MidAddress billingAddress;
    @JsonProperty("shipping_address")
    @SerializedName("shipping_address")
    MidAddress shippingAddress;

    public MidCustomerDetails() {
    }

    public MidCustomerDetails(String firstName, String lastName, String email, String phone, MidAddress billingAddress, MidAddress shippingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            MidCustomerDetails that = (MidCustomerDetails)o;
            if (this.billingAddress != null) {
                if (!this.billingAddress.equals(that.billingAddress)) {
                    return false;
                }
            } else if (that.billingAddress != null) {
                return false;
            }

            label74: {
                if (this.email != null) {
                    if (this.email.equals(that.email)) {
                        break label74;
                    }
                } else if (that.email == null) {
                    break label74;
                }

                return false;
            }

            if (this.firstName != null) {
                if (!this.firstName.equals(that.firstName)) {
                    return false;
                }
            } else if (that.firstName != null) {
                return false;
            }

            label60: {
                if (this.lastName != null) {
                    if (this.lastName.equals(that.lastName)) {
                        break label60;
                    }
                } else if (that.lastName == null) {
                    break label60;
                }

                return false;
            }

            if (this.phone != null) {
                if (!this.phone.equals(that.phone)) {
                    return false;
                }
            } else if (that.phone != null) {
                return false;
            }

            if (this.shippingAddress != null) {
                if (!this.shippingAddress.equals(that.shippingAddress)) {
                    return false;
                }
            } else if (that.shippingAddress != null) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.firstName != null ? this.firstName.hashCode() : 0;
        result = 31 * result + (this.lastName != null ? this.lastName.hashCode() : 0);
        result = 31 * result + (this.email != null ? this.email.hashCode() : 0);
        result = 31 * result + (this.phone != null ? this.phone.hashCode() : 0);
        result = 31 * result + (this.billingAddress != null ? this.billingAddress.hashCode() : 0);
        result = 31 * result + (this.shippingAddress != null ? this.shippingAddress.hashCode() : 0);
        return result;
    }
}
