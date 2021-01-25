package com.numeralasia.payment.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Component
@PropertySource("classpath:application.properties")
public class ServerUtils {

    @Autowired MessageSource messageSource ;


    @Value("${amazon.public.ip.checker}")
    private String amazonPublicIpChecker;

    @Value("${server.port}")
    private String serverPort;

    @Value("${http.ssl-mode}")
    private boolean sslMode ;

    @Value("${public.access-mode}")
    private boolean publicAccessMode ;

    @Value("${public.current-ip}")
    private String publicCurrentBackupIp ;

    @Value("${public.domain-api}")
    private String publicDomainApi ;
    @Value("${api}") String api ;

    String address ;

//    public static MessageSource message = null;


    @PostConstruct
    public void postConstruct(){
        this.address = publicDomainApi+api ;
//        Constant.message = messageSource;
//        ServerUtils.message = messageSource ;
    }

    public String address(){
        return address ;
    }

    public String getCurrentIp(){
        String currentIp = null ;

        if(publicAccessMode) {
            BufferedReader in = null;

            try {
                URL whatismyip = new URL(amazonPublicIpChecker);
                //        return "10.0.1.111";
                in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
                currentIp = in.readLine();
                currentIp = currentIp.trim();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            if (currentIp == null) {
                currentIp = publicCurrentBackupIp ; //InetAddress.getLocalHost().getHostAddress();
            }
        }catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        if(sslMode) {
            return "https://" + currentIp + ":"+ serverPort;
        }else {
            return "http://" + currentIp + ":"+ serverPort;
        }
    }

    public String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }




}
