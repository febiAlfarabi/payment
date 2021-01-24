package com.numeralasia.payment.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.numeralasia.payment.util.JsonDateTimeDeserializer;
import com.numeralasia.payment.util.JsonDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EBaseDto implements Serializable {

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

}
