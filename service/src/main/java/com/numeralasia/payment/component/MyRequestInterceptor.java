package com.numeralasia.payment.component;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Component
public class MyRequestInterceptor extends HandlerInterceptorAdapter {

    public static final String TAG = MyRequestInterceptor.class.getName();

    private final static Logger logger = LoggerFactory.getLogger(MyRequestInterceptor.class);

    @Autowired Gson gson ;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpServletRequest requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
        String body = "NO_PARAM" ;
        try {
            body = IOUtils.toString(requestCacheWrapperObject.getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String incomingRequest = "### INCOMING REQUEST ### "+requestCacheWrapperObject.getRequestURI()+" :: ";
        for (String httpHeaderName : Collections.list(requestCacheWrapperObject.getHeaderNames())) {
            String value = requestCacheWrapperObject.getHeader(httpHeaderName);
            logger.debug("===========>> Header name : {} #### Header value : {} ", httpHeaderName, value);
        }

        try {
            incomingRequest = incomingRequest+body;
            logger.debug(TAG+" : {}", incomingRequest);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if(ex!=null) {
            ex.printStackTrace();
            logger.error("### CONTAIN EXCEPTION ### {}", ExceptionUtils.getRootCauseMessage(ex));
        }
        try{
            logger.debug("### STATUS ### {}", response.getStatus());
            logger.debug("### HANDLER ### {}", handler);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
