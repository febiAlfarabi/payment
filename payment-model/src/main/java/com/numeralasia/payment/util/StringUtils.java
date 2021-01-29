package com.numeralasia.payment.util;

public class StringUtils {
    public static final boolean oneOf(String in, String... matchers){
        boolean match = false ;
        if(matchers==null){
            return match;
        }
        for (String matcher : matchers) {
            if(in.equalsIgnoreCase(matcher)){
                match = true ;
                break;
            }
        }
        return match;
    }

}
