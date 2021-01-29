package com.numeralasia.payment.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.numeralasia.payment.entity.client.Client;
import com.numeralasia.payment.service.client.ClientService;
import com.numeralasia.payment.util.Constant;
import com.numeralasia.payment.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
@Order(0)
public class AppStartInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(AppStartInitializer.class);

    @Autowired Gson gson ;

    @Autowired PasswordEncoder passwordEncoder ;

    @Autowired ClientService clientService ;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        logger.debug("### START GENERATING DATA ###");
        buildClient();

    }

    @Value("classpath:"+ Constant.MASTER_CLIENT_JSON_FILE)
    Resource masterClientResource ;
    void buildClient(){
        HashMap<Integer, String> map = new HashMap<>();
        int k = 1;

        try {
            String clientJson = Utils.readResourceAsString(masterClientResource);
            Type clientType = new TypeToken<List<Client>>() {}.getType();
            List<Client> clientList = gson.fromJson(clientJson, clientType);
            for (int i = 0; i < clientList.size(); i++) {
                Client client = clientList.get(i);
                if(!clientService.existsById(client.getId())){
                    client = clientService.save(client);
                    map.put(k++, "- Generate Client ### ID : "+ String.valueOf(client.getId()));
                }
                map.put(k++, "- Generate Client ### ID : " + String.valueOf(client.getId()));
            }
            logger.debug("############## FINISH BUILDING MASTER DATA CONTENT == {} ", map);
            logger.debug("############## FINISH BUILDING MASTER DATA AT " +
                    new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date())
                    + " ###################");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
