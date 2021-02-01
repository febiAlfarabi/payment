package com.numeralasia.payment.controller.midtrans;

import com.numeralasia.payment.controller.BasicController;
import com.numeralasia.payment.entity.client.Client;
import com.numeralasia.payment.entity.midtrans.TransactionGate;
import com.numeralasia.payment.model.WSResponse;
import com.numeralasia.payment.model.exception.AppException;
import com.numeralasia.payment.model.midtrans.MidTransactionStatus;
import com.numeralasia.payment.model.midtrans.MidtransChargeResponse;
import com.numeralasia.payment.service.midtrans.MidtransPaymentManager;
import com.numeralasia.payment.service.midtrans.TransactionGateService;
import com.numeralasia.payment.model.util.Constant;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.Arrays;

@RestController
@RequestMapping(path = "${api}")
public class MidtransController extends BasicController {

    Logger logger = LoggerFactory.getLogger(MidtransController.class);

    @Autowired MidtransPaymentManager midtransPaymentManager ;
    @Autowired TransactionGateService transactionGateService ;
    protected VtDirect vtDirect;

    @PostConstruct
    public void setup() {
        vtDirect = midtransPaymentManager.getVtGatewayFactory().vtDirect();
    }

    @PostMapping(path = "/midtrans/notification")
    public WSResponse notification(@RequestBody MidTransactionStatus midTransactionStatus) throws Exception {
        logger.debug("#### MIDTRANS NOTIFICATION LISTENER ##### {} ",gson.toJson(midTransactionStatus));
        String paymentId  = midTransactionStatus.getOrderId();
        VtResponse res = vtDirect.status(paymentId);
        TransactionGate transactionGate = transactionGateService.findByPaymentId(paymentId);
        Client client = transactionGate.getClient();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MidTransactionStatus> entity = new HttpEntity(midTransactionStatus, headers);

        Type type = new TypeToken<MidtransChargeResponse>(){}.getType();
        try{
            ResponseEntity<MidtransChargeResponse> responseEntity = msRestTemplate.exchange(client.getServiceAddress()+client.getNotificationEndpoint(), HttpMethod.POST, entity, ParameterizedTypeReference.forType(type));
            if(responseEntity.getStatusCode()==HttpStatus.CREATED){
                MidtransChargeResponse midtransChargeResponse = responseEntity.getBody();
                logger.debug("BODY RESPONSE {} ", midtransChargeResponse);
                return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS);
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


}
