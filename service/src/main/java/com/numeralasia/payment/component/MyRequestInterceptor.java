package com.numeralasia.payment.component;

import com.google.gson.Gson;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        Map map = requestCacheWrapperObject.getParameterMap();
        String incomingRequest = "### INCOMING REQUEST ### "+request.getRequestURI()+" :: ";
        for (String httpHeaderName : Collections.list(request.getHeaderNames())) {
            String value = request.getHeader(httpHeaderName);
            logger.debug("===========>> Header name : {} #### Header value : {} ", httpHeaderName, value);
        }

        String json = "NO_PARAM";
        try {
            json = gson.toJson(map);
            incomingRequest = incomingRequest+json;
            logger.debug(TAG+" : {}", incomingRequest);
//            String authorization = jwtTokenProvider.resolveToken(request);
//            if(authorization!=null){
//                logger.debug("AUTHORIZATION", authorization);
//
//                String email = jwtTokenProvider.getUsername(authorization);
//                List<String> roles = jwtTokenProvider.getRoles(authorization);
//                authorization = jwtTokenProvider.createToken(email, roles);
//                response.setHeader(Constant.AUTHORIZATION, authorization);
//                logger.debug(" New Authorization for Login id : {}, With role {}, in a response {} ", email, roles, authorization);
//            }
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
