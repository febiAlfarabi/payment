package com.numeralasia.payment.model.midtrans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class MidtransChargeResponse implements Serializable {

    @JsonProperty("token")
    @SerializedName("token")
    String token;

    @JsonProperty("redirect_url")
    @SerializedName("redirect_url")
    String redirectUrl;


    @JsonProperty("client_key")
    @SerializedName("client_key")
    String clientKey;

}
