package com.numeralasia.payment.model.midtrans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.numeralasia.payment.model.jurnal.VaNumber;
import com.numeralasia.payment.util.StringUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class MidTransactionStatus implements Serializable {

    public static final String SUCCESS_CODE = "200";
    public static final String PENDING_CODE = "201";
    public static final String DENIED_CODE = "202";
    public static final String EXPIRED_CODE = "407";

    public static final String TRANSACTION_STATUS_AUTHORIZED = "authorize";
    public static final String TRANSACTION_STATUS_CAPTURE = "capture";
    public static final String TRANSACTION_STATUS_SETTLED = "settlement";
    public static final String TRANSACTION_STATUS_PENDING = "pending";
    public static final String TRANSACTION_STATUS_CANCELLED = "cancel";
    public static final String TRANSACTION_STATUS_DENIED = "deny";
    public static final String TRANSACTION_STATUS_EXPIRED = "expire";
    public static final String TRANSACTION_STATUS_FAILED = "failure";

    public static final String FRAUD_STATUS_ACCEPT = "accept";
    public static final String FRAUD_STATUS_CHALLENGE = "challenge";
    public static final String FRAUD_STATUS_DENY = "deny";

    public boolean isSucceed(){
        return (
                org.apache.commons.lang.StringUtils.equals(statusCode, SUCCESS_CODE) &&
                org.apache.commons.lang.StringUtils.equals(fraudStatus, FRAUD_STATUS_ACCEPT) &&
                        StringUtils.
                                oneOf(transactionStatus, TRANSACTION_STATUS_CAPTURE,
                                        TRANSACTION_STATUS_SETTLED));
    }

    public boolean isExpired(){
        return (
                org.apache.commons.lang.StringUtils.equals(transactionStatus, TRANSACTION_STATUS_EXPIRED));
    }

    public boolean isDenied(){
        return (
                org.apache.commons.lang.StringUtils.equals(statusCode, DENIED_CODE)
                        && org.apache.commons.lang.StringUtils.equals(transactionStatus, TRANSACTION_STATUS_DENIED));
    }

    public boolean isCanceled(){
        return (
                org.apache.commons.lang.StringUtils.equals(transactionStatus, TRANSACTION_STATUS_CANCELLED));
    }


    @JsonProperty("status_code")
    @SerializedName("status_code")
    String statusCode;

    @JsonProperty("status_message")
    @SerializedName("status_message")
    String statusMessage;

    @JsonProperty("transaction_id")
    @SerializedName("transaction_id")
    String transactionId;

    @JsonProperty("order_id")
    @SerializedName("order_id")
    String orderId;

    @JsonProperty("gross_amount")
    @SerializedName("gross_amount")
    String grossAmount;

    @JsonProperty("payment_type")
    @SerializedName("payment_type")
    String paymentType;

    @JsonProperty("transaction_time")
    @SerializedName("transaction_time")
    String transactionTime;

    @JsonProperty("settlement_time")
    @SerializedName("settlement_time")
    String settlementTime;


    @JsonProperty("transaction_status")
    @SerializedName("transaction_status")
    String transactionStatus;

    @JsonProperty("va_numbers")
    @SerializedName("va_numbers")
    List<VaNumber> vaNumbers = new ArrayList<>();

    @JsonProperty("fraud_status")
    @SerializedName("fraud_status")
    String fraudStatus;

    @JsonProperty("bca_va_number")
    @SerializedName("bca_va_number")
    String bcaVaNumber;

    @JsonProperty("pdf_url")
    @SerializedName("pdf_url")
    String pdfUrl;

    @JsonProperty("finish_redirect_url")
    @SerializedName("finish_redirect_url")
    String finishRedirectUrl;

    @JsonProperty("masked_card")
    @SerializedName("masked_card")
    String maskedCard ;

    @JsonProperty("approval_code")
    @SerializedName("approval_code")
    String approvalCode ;

    @JsonProperty("bank")
    @SerializedName("bank")
    String bank ;

    @JsonProperty("channel_response_code")
    @SerializedName("channel_response_code")
    String channelResponseCode ;

    @JsonProperty("channel_response_message")
    @SerializedName("channel_response_message")
    String channelResponseMessage ;

    @JsonProperty("currency")
    @SerializedName("currency")
    String currency ;

    @JsonProperty("signature_key")
    @SerializedName("signature_key")
    String signatureKey ;

    @JsonProperty("card_type")
    @SerializedName("card_type")
    String cardType ;


}
