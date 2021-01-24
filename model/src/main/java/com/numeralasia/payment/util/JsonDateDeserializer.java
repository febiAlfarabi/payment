package com.numeralasia.payment.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateDeserializer extends JsonDeserializer {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        SimpleDateFormat format = new SimpleDateFormat(DateAppConfig.API_DATE);
        String date = jsonParser.getText();
        try {
            if(StringUtils.isEmpty(date)){
                return null ;
            }
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null ;
        }

    }
}
