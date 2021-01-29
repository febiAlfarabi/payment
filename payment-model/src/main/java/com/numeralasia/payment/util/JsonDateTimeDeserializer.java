package com.numeralasia.payment.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateTimeDeserializer extends JsonDeserializer {

    SimpleDateFormat dateTimeformat = new SimpleDateFormat(Constant.API_DATE_TIME_FORMAT);
    SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.API_DATE_FORMAT);
    SimpleDateFormat dateTimeZoneFormatter = new SimpleDateFormat(Constant.DATE_TIMEZONE_FORMAT);
    Logger logger = LoggerFactory.getLogger(JsonDateTimeDeserializer.class);

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        boolean success = false ;
        String dateString = jsonParser.getText();
        if(StringUtils.isNotEmpty(dateString)){
            dateString = dateString.replace("T", " ");
        }
        if(!success){
            try {
                if(StringUtils.isEmpty(dateString)){
                    return null ;
                }
                Date date = dateTimeZoneFormatter.parse(dateString);
                success = true ;
                return date;
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        if(!success){
            try {
                if(StringUtils.isEmpty(dateString)){
                    return null ;
                }
                Date date = dateTimeformat.parse(dateString);
                success = true ;
                return date;
            } catch (Exception e) {
//                e.printStackTrace();

            }
        }
        if(!success){
            try {
                if(StringUtils.isEmpty(dateString)){
                    return null ;
                }
                Date date = dateFormat.parse(dateString);
                success = true ;
                return date;
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        return null ;
    }
}