package com.numeralasia.payment.model.jurnal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class VaNumber implements Serializable {

    String bank;
    @JsonProperty("va_number")
    @SerializedName("va_number")
    String vaNumber;

}
