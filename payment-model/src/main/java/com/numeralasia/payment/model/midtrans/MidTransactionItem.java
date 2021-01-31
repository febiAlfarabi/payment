//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.numeralasia.payment.model.midtrans;

import lombok.Data;

import java.io.Serializable;

@Data
public class MidTransactionItem implements Serializable {
    String id;
    String name;
    Long price;
    Integer quantity;

    public MidTransactionItem() {
    }

    public MidTransactionItem(String id, String name, Long price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            MidTransactionItem that;
            label57: {
                that = (MidTransactionItem)o;
                if (this.id != null) {
                    if (this.id.equals(that.id)) {
                        break label57;
                    }
                } else if (that.id == null) {
                    break label57;
                }

                return false;
            }

            label50: {
                if (this.name != null) {
                    if (this.name.equals(that.name)) {
                        break label50;
                    }
                } else if (that.name == null) {
                    break label50;
                }

                return false;
            }

            if (this.price != null) {
                if (!this.price.equals(that.price)) {
                    return false;
                }
            } else if (that.price != null) {
                return false;
            }

            if (this.quantity != null) {
                if (!this.quantity.equals(that.quantity)) {
                    return false;
                }
            } else if (that.quantity != null) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.price != null ? this.price.hashCode() : 0);
        result = 31 * result + (this.quantity != null ? this.quantity.hashCode() : 0);
        return result;
    }
}
