package com.numeralasia.payment.model.midtrans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import com.numeralasia.payment.model.util.JsonDateTimeDeserializer;
import com.numeralasia.payment.model.util.JsonDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CustomExpiry implements Serializable {

    public static final String SECOND = "second";
    public static final String MINUTE = "minute";
    public static final String HOUR = "hour";
    public static final String DAY = "day";


    @JsonProperty("order_time")
    @SerializedName("order_time")
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    Date orderTime ;
    @JsonProperty("expiry_duration")
    @SerializedName("expiry_duration")
    Integer expiryDuration ;
    @JsonProperty("unit")
    @SerializedName("unit")
    String unit ;

}
