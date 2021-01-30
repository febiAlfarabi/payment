package com.numeralasia.payment.component;

import com.numeralasia.payment.util.CachedBodyHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = "/api/*", asyncSupported = true)
public class OverrideResponseHeaderFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(OverrideResponseHeaderFilter.class);



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request ;
        CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(httpServletRequest);


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
