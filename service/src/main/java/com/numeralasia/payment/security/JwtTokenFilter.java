package com.numeralasia.payment.security;

import com.numeralasia.payment.model.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

//    private JwtTokenProvider jwtTokenProvider;
//
//    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }

    public JwtTokenFilter(){

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
        throws IOException, ServletException, AppException {
        filterChain.doFilter(req, res);
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
//        if(token!=null){
//            List<String> roles = jwtTokenProvider.getRoles(token);
//            logger.debug("roles {}", roles);
//            if(roles==null || roles.size()<=0){
//                filterChain.doFilter(req, res);
//            }else{
//                String role = roles.get(0);
//                if(role!=null && role.equalsIgnoreCase(Constant.ADMIN)){
//                    if (token != null && jwtTokenProvider.validateToken(token)) {
//                        Authentication auth = jwtTokenProvider.getAdminAuthentication(token);
//                        if (auth != null) {
//                            SecurityContextHolder.getContext().setAuthentication(auth);
//                        }
//                    }
//                }else{
//                    if (token != null && jwtTokenProvider.validateToken(token)) {
//                        Authentication auth = jwtTokenProvider.getUserAuthentication(token);
//                        if (auth != null) {
//                            SecurityContextHolder.getContext().setAuthentication(auth);
//                        }
//                    }
//                }
//            }
//            filterChain.doFilter(req, res);
//        }else{
//            filterChain.doFilter(req, res);
//        }
    }


}
