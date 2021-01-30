package com.numeralasia.payment.model.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateSerializer extends JsonSerializer<Date> {


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DateAppConfig.API_DATE);

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        try {
            if(date==null){
                return;
            }
            String formattedDate = dateFormat.format(date);
            jsonGenerator.writeString(formattedDate);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
