package com.numeralasia.payment.model.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.numeralasia.payment.model.util.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateTimeSerializer extends JsonSerializer<Date> {


    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(Constant.API_DATE_TIME_FORMAT);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.API_DATE_FORMAT);
    private static final SimpleDateFormat dateTimeZoneFormatter = new SimpleDateFormat(Constant.DATE_TIMEZONE_FORMAT);

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {

        boolean success = false ;
        if(!success){
            try {
                if(date==null){
                    return;
                }
                String formattedDate = dateTimeFormat.format(date);
                jsonGenerator.writeString(formattedDate);
                success = true ;
            }catch (Exception e){
//                e.printStackTrace();
            }
        }
        if(!success){
            try {
                if(date==null){
                    return;
                }
                String formattedDate = dateTimeZoneFormatter.format(date);
                jsonGenerator.writeString(formattedDate);
                success = true ;
            }catch (Exception e){
//                e.printStackTrace();
            }
        }
        if(!success){
            try {
                if(date==null){
                    return;
                }
                String formattedDate = dateFormat.format(date);
                jsonGenerator.writeString(formattedDate);
                success = true ;
            }catch (Exception e){
//                e.printStackTrace();
            }
        }

    }
}
