package com.numeralasia.payment.model.payment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.numeralasia.payment.model.util.JsonDateTimeDeserializer;
import com.numeralasia.payment.model.util.JsonDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
public class MidtransPaymentStatusDto implements Serializable {

    public static final String MIDTRANS_SNAP_ORDER = "snap_order";
    public static final String MIDTRANS_SNAP_PENDING = "snap_pending";
    public static final String MIDTRANS_SNAP_ERROR = "snap_error";
    public static final String MIDTRANS_SNAP_CLOSED = "snap_closed";

    public static final String MIDTRANS_AUTHORIZED = "authorize";
    public static final String MIDTRANS_CAPTURE = "capture";
    public static final String MIDTRANS_SETTLED = "settlement";
    public static final String MIDTRANS_PENDING = "pending";
    public static final String MIDTRANS_CANCELLED = "cancel";
    public static final String MIDTRANS_DENIED = "deny";
    public static final String MIDTRANS_EXPIRED = "expire";
    public static final String MIDTRANS_FAILED = "failure";

    public static final String MIDTRANS_PAYMENT_STATUSES =
                            MIDTRANS_SNAP_ORDER +","+
                            MIDTRANS_SNAP_PENDING +","+
                            MIDTRANS_SNAP_ERROR +","+
                            MIDTRANS_SNAP_CLOSED +","+
                            MIDTRANS_AUTHORIZED +","+
                            MIDTRANS_CAPTURE +","+
                            MIDTRANS_SETTLED +","+
                            MIDTRANS_PENDING +","+
                            MIDTRANS_CANCELLED +","+
                            MIDTRANS_DENIED +","+
                            MIDTRANS_EXPIRED +","+
                            MIDTRANS_FAILED ;

    String name ;

    String note ;
    boolean notified ;

    protected Long id ;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    protected Date created = new Date();
    protected Long createdBy ;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    protected Date updated = new Date();

    protected Long updatedBy ;
    protected Boolean active ;



    public static final List<Map> midtransPaymentStatuses(){
        List<Map> maps = new ArrayList<>();
        for (int i = 0; i < MIDTRANS_PAYMENT_STATUSES.split(",").length; i++) {
            Map map = new HashMap();
            map.put("id", i+1);
            map.put("name", MIDTRANS_PAYMENT_STATUSES.split(",")[i]);
            maps.add(map);
        }
        return maps;

    }





}
