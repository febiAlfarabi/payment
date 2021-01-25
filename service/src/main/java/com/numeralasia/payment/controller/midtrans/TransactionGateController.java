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
import com.numeralasia.payment.util.Base64;
import com.numeralasia.payment.util.Constant;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

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

    @PostMapping(path = "/snap/v1/transactions")
    public ResponseEntity<MidtransChargeResponse> notification(@RequestHeader(Constant.REFERENCE) String referenceBase64, @RequestBody MidChargeRequest midChargeRequest) throws Exception {
        String reference = new String(Base64.decode(referenceBase64, Base64.NO_WRAP));
        logger.debug("REFERENCE : {} ", reference);
        Client client = clientService.findByReference(reference);
        TransactionGate transactionGate = new TransactionGate();
        transactionGate.setClient(client);
        transactionGate.setOrderId(midChargeRequest.getTransactionDetails().getOrderId());
        MidtransChargeResponse midtransChargeResponse = midtransPaymentManager.charge(midChargeRequest);
        transactionGate = transactionGateService.save(transactionGate);
        return ResponseEntity.ok(midtransChargeResponse);
    }

    @GetMapping(path = "/v2/{refCode}/status")
    public ResponseEntity<MidTransactionStatus> notification(@PathVariable String refCode) {
        MidTransactionStatus midTransactionStatus = midtransPaymentManager.checkStatus(refCode);
        return ResponseEntity.ok(midTransactionStatus);
    }


}
