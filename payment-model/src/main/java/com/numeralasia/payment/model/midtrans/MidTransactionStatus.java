package com.numeralasia.payment.model.midtrans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.numeralasia.payment.model.jurnal.VaNumber;
import io.github.febialfarabi.utility.StringUtils;
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
    public static final String DOESNT_EXIST = "404";

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

    public boolean isPending(){
        return (
                org.apache.commons.lang.StringUtils.equals(statusCode, PENDING_CODE) &&
                        org.apache.commons.lang.StringUtils.equals(transactionStatus, TRANSACTION_STATUS_PENDING));
    }

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
                (org.apache.commons.lang.StringUtils.equals(statusCode, DENIED_CODE)
                        && org.apache.commons.lang.StringUtils.equals(transactionStatus, TRANSACTION_STATUS_DENIED))
                ||
                org.apache.commons.lang.StringUtils.equals(statusCode, DOESNT_EXIST));
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
    @JsonProperty("saved_token_id")
    @SerializedName("saved_token_id")
    String savedTokenId;
    @JsonProperty("masked_card")
    @SerializedName("masked_card")
    String maskedCard;
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
    @JsonProperty("transaction_status")
    @SerializedName("transaction_status")
    String transactionStatus;
    @JsonProperty("fraud_status")
    @SerializedName("fraud_status")
    String fraudStatus;
    @JsonProperty("saved_token_id_expired_at")
    @SerializedName("saved_token_id_expired_at")
    String savedTokenIdExpiredAt;
    @JsonProperty("approval_code")
    @SerializedName("approval_code")
    String approvalCode;
    @JsonProperty("secure_token")
    @SerializedName("secure_token")
    boolean secureToken;
    @JsonProperty("permata_va_number")
    @SerializedName("permata_va_number")
    String permataVANumber;
    @JsonProperty("permata_expiration")
    @SerializedName("permata_expiration")
    String permataExpiration;
    @JsonProperty("va_numbers")
    @SerializedName("va_numbers")
    List<VaNumber> accountNumbers;
    @JsonProperty("bca_klikbca_expire_time")
    @SerializedName("bca_klikbca_expire_time")
    String bcaKlikBcaExpiration;
    @JsonProperty("bca_va_number")
    @SerializedName("bca_va_number")
    String bcaVaNumber;
    @JsonProperty("bca_expiration")
    @SerializedName("bca_expiration")
    String bcaExpiration;
    @JsonProperty("bni_va_number")
    @SerializedName("bni_va_number")
    String bniVaNumber;
    @JsonProperty("bni_expiration")
    @SerializedName("bni_expiration")
    String bniExpiration;
    @JsonProperty("bri_va_number")
    @SerializedName("bri_va_number")
    String briVaNumber;
    @JsonProperty("bri_expiration")
    @SerializedName("bri_expiration")
    String briExpiration;
    @JsonProperty("billpayment_expiration")
    @SerializedName("billpayment_expiration")
    String mandiriBillExpiration;
    @JsonProperty("xl_tunai_order_id")
    @SerializedName("xl_tunai_order_id")
    String xlTunaiOrderId;
    @JsonProperty("xl_tunai_merchant_id")
    @SerializedName("xl_tunai_merchant_id")
    String xlTunaiMerchantId;
    @JsonProperty("xl_expiration")
    @SerializedName("xl_expiration")
    String xlTunaiExpiration;
    @JsonProperty("installment_term")
    @SerializedName("installment_term")
    String installmentTerm;
    @JsonProperty("gopay_expiration")
    @SerializedName("gopay_expiration")
    String gopayExpiration;
    @JsonProperty("alfamart_expire_time")
    @SerializedName("alfamart_expire_time")
    String alfamartExpireTime;
    @JsonProperty("gopay_expiration_raw")
    @SerializedName("gopay_expiration_raw")
    String gopayExpirationRaw;
    @JsonProperty("indomaret_expire_time")
    @SerializedName("indomaret_expire_time")
    String indomaretExpireTime;
    @JsonProperty("redirect_url")
    @SerializedName("redirect_url")
    String redirectUrl;
    @JsonProperty("pdf_url")
    @SerializedName("pdf_url")
    String pdfUrl;
    String bank;
    String eci;
    @JsonProperty("bill_key")
    @SerializedName("bill_key")
    String paymentCode;
    @JsonProperty("biller_code")
    @SerializedName("biller_code")
    String companyCode;
    @JsonProperty("payment_code")
    @SerializedName("payment_code")
    String paymentCodeResponse;
    @JsonProperty("finish_redirect_url")
    @SerializedName("finish_redirect_url")
    String finishRedirectUrl;
    @JsonProperty("kioson_expire_time")
    @SerializedName("kioson_expire_time")
    String kiosonExpireTime;
    @JsonProperty("validation_messages")
    @SerializedName("validation_messages")
    ArrayList<String> validationMessages;
    @JsonProperty("point_balance")
    @SerializedName("point_balance")
    float pointBalance;
    @JsonProperty("point_balance_amount")
    @SerializedName("point_balance_amount")
    String pointBalanceAmount;
    @JsonProperty("point_redeem_amount")
    @SerializedName("point_redeem_amount")
    float pointRedeemAmount;
    @JsonProperty("qr_code_url")
    @SerializedName("qr_code_url")
    String qrCodeUrl;
    @JsonProperty("deeplink_url")
    @SerializedName("deeplink_url")
    String deeplinkUrl;
    @JsonProperty("qris_url")
    @SerializedName("qris_url")
    String qrisUrl;
    String currency;



}
