//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.numeralasia.payment.model.midtrans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MidAddress {
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    String address;
    String city;
    @JsonProperty("postal_code")
    String postalCode;
    String phone;
    @JsonProperty("country_code")
    String countryCode;

    public MidAddress() {
    }

    public MidAddress(String firstName, String lastName, String address, String city, String postalCode, String phone, String countryCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
        this.countryCode = countryCode;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            MidAddress that;
            label93: {
                that = (MidAddress)o;
                if (this.address != null) {
                    if (this.address.equals(that.address)) {
                        break label93;
                    }
                } else if (that.address == null) {
                    break label93;
                }

                return false;
            }

            label86: {
                if (this.city != null) {
                    if (this.city.equals(that.city)) {
                        break label86;
                    }
                } else if (that.city == null) {
                    break label86;
                }

                return false;
            }

            if (this.countryCode != null) {
                if (!this.countryCode.equals(that.countryCode)) {
                    return false;
                }
            } else if (that.countryCode != null) {
                return false;
            }

            label72: {
                if (this.firstName != null) {
                    if (this.firstName.equals(that.firstName)) {
                        break label72;
                    }
                } else if (that.firstName == null) {
                    break label72;
                }

                return false;
            }

            label65: {
                if (this.lastName != null) {
                    if (this.lastName.equals(that.lastName)) {
                        break label65;
                    }
                } else if (that.lastName == null) {
                    break label65;
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

            if (this.postalCode != null) {
                if (!this.postalCode.equals(that.postalCode)) {
                    return false;
                }
            } else if (that.postalCode != null) {
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
        result = 31 * result + (this.address != null ? this.address.hashCode() : 0);
        result = 31 * result + (this.city != null ? this.city.hashCode() : 0);
        result = 31 * result + (this.postalCode != null ? this.postalCode.hashCode() : 0);
        result = 31 * result + (this.phone != null ? this.phone.hashCode() : 0);
        result = 31 * result + (this.countryCode != null ? this.countryCode.hashCode() : 0);
        return result;
    }
}
