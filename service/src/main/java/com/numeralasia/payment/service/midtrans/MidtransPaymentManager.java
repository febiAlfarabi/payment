package com.numeralasia.payment.service.midtrans;

import com.google.gson.Gson;
import com.numeralasia.payment.model.exception.AppException;
import com.numeralasia.payment.model.midtrans.MidChargeRequest;
import com.numeralasia.payment.model.midtrans.MidTransactionStatus;
import com.numeralasia.payment.model.midtrans.MidtransChargeResponse;
import com.numeralasia.payment.util.Constant;
import id.co.veritrans.mdk.v1.VtGatewayConfigBuilder;
import id.co.veritrans.mdk.v1.VtGatewayFactory;
import id.co.veritrans.mdk.v1.config.EnvironmentType;
import io.github.febialfarabi.utility.MSRestTemplate;
import lombok.Getter;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by gde on 5/14/15.
 */
@Component
public class MidtransPaymentManager {

    Logger logger = LoggerFactory.getLogger(MidtransPaymentManager.class);


    @Getter @Value("${midtrans.base-domain}") String  midtransBaseDomain ;
    @Getter @Value("${midtrans.api-domain}") String  midtransApiDomain ;
    @Getter @Value("${midtrans.snap-api}") String  midtransSnapApi ;
    @Getter @Value("${midtrans.simulator}") String  midtransSimulator ;

    @Getter @Value("${midtrans.merchant-id}") String  midtransMerchantId ;
    @Getter @Value("${midtrans.client-key}") String midtransClientKey ;
    @Getter @Value("${midtrans.server.key}") String midtransServerKey ;
    @Getter @Value("${midtrans.environment}") String midtransEnvironment ;
    @Getter @Value("${midtrans.connection.pool}") Integer midtransConnectionPool ;

    @Autowired MSRestTemplate msRestTemplate ;

    private VtGatewayFactory vtGatewayFactory;
    @Autowired Gson gson ;

    @PostConstruct
    public void setup() {
        try{
            vtGatewayFactory = new VtGatewayFactory(new VtGatewayConfigBuilder()
                    .setClientKey(midtransClientKey)
                    .setServerKey(midtransServerKey)
                    .setEnvironmentType(EnvironmentType.valueOf(midtransEnvironment))
                    .setMaxConnectionPoolSize(midtransConnectionPool)
                    .createVtGatewayConfig()
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    @Getter
//    @Setter
//    MidChargeRequest midChargeRequest ;


    public MidtransChargeResponse charge(MidChargeRequest midChargeRequest){
        String url = midtransBaseDomain+"/snap/v1/transactions";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, "Basic "+Base64.getEncoder().encodeToString((midtransServerKey+":").getBytes()));
        HttpEntity<MidChargeRequest> entity = new HttpEntity(midChargeRequest, headers);
        logger.debug("MID CHARGE REQUEST "+gson.toJson(midChargeRequest));
        logger.debug("HIT :: {} ", url);
        headers.entrySet().forEach(stringListEntry -> {
            logger.debug("HIT Header name : {} #### Header value : {} ", stringListEntry.getKey(), stringListEntry.getValue());
        });

        Type type = new TypeToken<MidtransChargeResponse>(){}.getType();
        try{
            ResponseEntity<MidtransChargeResponse> responseEntity = msRestTemplate.exchange(url, HttpMethod.POST, entity, ParameterizedTypeReference.forType(type));
            if(responseEntity.getStatusCode()==HttpStatus.CREATED){
                MidtransChargeResponse midtransChargeResponse = responseEntity.getBody();
                logger.debug("BODY RESPONSE {} ", midtransChargeResponse);
                return midtransChargeResponse;
            }else{
                throw new AppException(Constant.FAILED_CODE, responseEntity.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
            String body = e.getMessage();
            logger.debug("BODY RESPONSE {} ", body);
            throw new AppException(Constant.FAILED_CODE, body);
        }
    }

    public MidTransactionStatus transactionStatus(String refCode) {
        String url = midtransApiDomain + "/v2/" + refCode + "/status";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString((midtransServerKey + ":").getBytes()));
        HttpEntity<MidChargeRequest> entity = new HttpEntity(headers);
        logger.debug("HIT :: {} ", url);
        headers.entrySet().forEach(stringListEntry -> {
            logger.debug("HIT Header name : {} #### Header value : {} ", stringListEntry.getKey(), stringListEntry.getValue());
        });

        Type type = new TypeToken<MidTransactionStatus>() {}.getType();
        try {
            ResponseEntity responseEntity = msRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                String bodyString = responseEntity.getBody().toString();
                MidTransactionStatus midTransactionStatus = gson.fromJson(bodyString, type);
                logger.debug("BODY RESPONSE {} ", midTransactionStatus);
                return midTransactionStatus;
            } else {
                throw new AppException(Constant.FAILED_CODE, responseEntity.toString());
            }
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            String body = e.getResponseBodyAsString();
            logger.debug("BODY RESPONSE {} ", body);
            throw new AppException(Constant.FAILED_CODE, body);

        }

    }

    public VtGatewayFactory getVtGatewayFactory() {
        return vtGatewayFactory;
    }



}
