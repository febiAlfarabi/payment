package com.numeralasia.payment.service.client;

import com.google.gson.reflect.TypeToken;
import com.numeralasia.payment.entity.client.Client;
import com.numeralasia.payment.model.exception.AppException;
import com.numeralasia.payment.repository.EBaseRepository;
import com.numeralasia.payment.repository.client.ClientRepository;
import com.numeralasia.payment.service.BasicRepoService;
import com.numeralasia.payment.util.Constant;
import com.numeralasia.payment.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ClientService extends BasicRepoService<Client> {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);


    @Autowired private ClientRepository repository ;

    @Override
    public EBaseRepository<Client> repository() {
        return repository;
    }

    @Override
    @PostConstruct
    public void construct() {
        super.construct();
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
                if(!existsById(client.getId())){
                    client = save(client);
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


    public Client findByReference(String reference){
        return repository.findByReference(reference).orElseThrow(() -> new AppException(Constant.FAILED_CODE, reference+" : not found"));
    }



}
