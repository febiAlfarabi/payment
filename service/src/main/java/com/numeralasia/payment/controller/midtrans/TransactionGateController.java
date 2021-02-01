package com.numeralasia.payment.controller.midtrans;

import com.numeralasia.payment.controller.BasicController;
import com.numeralasia.payment.entity.client.Client;
import com.numeralasia.payment.entity.midtrans.TransactionGate;
import com.numeralasia.payment.model.midtrans.MidChargeRequest;
import com.numeralasia.payment.model.midtrans.MidTransactionStatus;
import com.numeralasia.payment.model.midtrans.MidtransChargeResponse;
import com.numeralasia.payment.service.client.ClientService;
import com.numeralasia.payment.service.midtrans.MidtransPaymentManager;
import com.numeralasia.payment.service.midtrans.TransactionGateService;
import com.numeralasia.payment.model.util.Constant;
import com.numeralasia.payment.util.CachedBodyHttpServletRequest;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import io.github.febialfarabi.utility.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

@RestController
@RequestMapping(path = "${api}")
public class TransactionGateController extends BasicController {

    Logger logger = LoggerFactory.getLogger(TransactionGateController.class);

    @Autowired MidtransPaymentManager midtransPaymentManager ;
    @Autowired TransactionGateService transactionGateService ;
    @Autowired ClientService clientService ;
    protected VtDirect vtDirect;

    @PostConstruct
    public void setup() {
        vtDirect = midtransPaymentManager.getVtGatewayFactory().vtDirect();
    }

    @PostMapping(path = "/snap/v1/transactions",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MidtransChargeResponse transactions(@RequestHeader(Constant.REFERENCE) String referenceBase64,
                                               @RequestBody(required = false) MidChargeRequest midChargeRequest, HttpServletRequest request) throws Exception {
        String reference = new String(Base64.decode(referenceBase64, Base64.NO_WRAP));
        Client client = clientService.findByReference(reference);

        /*START GENERATE PAYMENT ID AND REVERSE ORDER ID*/
        String orderId = midChargeRequest.getTransactionDetails().getOrderId();
        String paymentId = transactionGateService.buildPaymentId(client, orderId);
        midChargeRequest.getTransactionDetails().setOrderId(paymentId);
        midChargeRequest.getTransactionDetails().setPaymentId(paymentId);
        /*END GENERATE PAYMENT ID AND REVERSED ORDER ID*/

        TransactionGate transactionGate = new TransactionGate();
        transactionGate.setClient(client);
        transactionGate.setOrderId(orderId);
        transactionGate.setPaymentId(paymentId);

        MidtransChargeResponse midtransChargeResponse = midtransPaymentManager.charge(midChargeRequest);
        midtransChargeResponse.setPaymentId(paymentId);
        transactionGate = transactionGateService.save(transactionGate);
        return midtransChargeResponse;
    }

    @PostMapping(path = "/v2/{paymentId}/cancel")
    @ResponseBody
    public ResponseEntity<MidTransactionStatus> cancelPayment(
            @RequestHeader(Constant.REFERENCE) String referenceBase64,
            @PathVariable String paymentId) {
        String reference = new String(Base64.decode(referenceBase64, Base64.NO_WRAP));
        Client client = clientService.findByReference(reference);
        MidTransactionStatus midTransactionStatus = midtransPaymentManager.cancelPayment(paymentId);
        return ResponseEntity.ok(midTransactionStatus);
    }
    @PostMapping(path = "/v2/{paymentId}/expire")
    @ResponseBody
    public ResponseEntity<MidTransactionStatus> expirePayment(
            @RequestHeader(Constant.REFERENCE) String referenceBase64,
            @PathVariable String paymentId) {
        String reference = new String(Base64.decode(referenceBase64, Base64.NO_WRAP));
        Client client = clientService.findByReference(reference);
        MidTransactionStatus midTransactionStatus = midtransPaymentManager.expirePayment(paymentId);
        return ResponseEntity.ok(midTransactionStatus);
    }
    @PostMapping(path = "/v2/{paymentId}/status")
    @ResponseBody
    public ResponseEntity<MidTransactionStatus> checkStatus(
            @RequestHeader(Constant.REFERENCE) String referenceBase64,
            @PathVariable String paymentId) {
        String reference = new String(Base64.decode(referenceBase64, Base64.NO_WRAP));
        Client client = clientService.findByReference(reference);
        MidTransactionStatus midTransactionStatus = midtransPaymentManager.checkStatus(paymentId);
        return ResponseEntity.ok(midTransactionStatus);
    }



}
