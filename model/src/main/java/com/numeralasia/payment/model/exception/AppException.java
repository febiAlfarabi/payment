package com.numeralasia.payment.model.exception;

import lombok.Getter;
import lombok.Setter;

public class AppException extends RuntimeException {

    @Getter
    @Setter
    private int code ;
    @Getter
    @Setter
    private String messageError ;

    public AppException(int code, String message){
        super(message);
        this.code = code;
    }

    public AppException(int code, String message, String messageError){
        super(message);
        this.messageError = messageError ;
        this.code = code;
    }


}
