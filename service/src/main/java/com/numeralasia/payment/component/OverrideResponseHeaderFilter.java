package com.numeralasia.payment.component;

import com.numeralasia.payment.util.CachedBodyHttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebFilter(value = "/api/*", asyncSupported = true)
public class OverrideResponseHeaderFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(OverrideResponseHeaderFilter.class);



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request ;
        CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(httpServletRequest);
        String body = "NO_PARAM" ;
        try {
            body = IOUtils.toString(cachedBodyHttpServletRequest.getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String incomingRequest = "### INCOMING REQUEST ### "+cachedBodyHttpServletRequest.getRequestURI()+" :: ";
        for (String httpHeaderName : Collections.list(cachedBodyHttpServletRequest.getHeaderNames())) {
            String value = cachedBodyHttpServletRequest.getHeader(httpHeaderName);
            logger.debug("===========>> Header name : {} #### Header value : {} ", httpHeaderName, value);
        }

        try {
            incomingRequest = incomingRequest+body;
            logger.debug(OverrideResponseHeaderFilter.class.getName()+" : {}", incomingRequest);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        chain.doFilter(cachedBodyHttpServletRequest, httpServletResponse);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // ...
    }

    @Override
    public void destroy() {
        // ...
    }
}
