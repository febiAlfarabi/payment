package com.numeralasia.payment.controller.client;

import com.numeralasia.payment.controller.BasicController;
import com.numeralasia.payment.entity.client.Client;
import com.numeralasia.payment.model.WSResponse;
import com.numeralasia.payment.model.client.ClientDto;
import com.numeralasia.payment.model.util.Constant;
import com.numeralasia.payment.service.client.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "${api}")
public class ClientController extends BasicController {

    @Autowired ClientService clientService ;

    @Value("${corporate.mode}") Boolean corporateMode ;
    @Value("${personal.mode}") Boolean personalMode ;


    @GetMapping(path = "/clients")
    @ApiOperation(value = "")
    public WSResponse findAll(
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "name") String sortir,
            @RequestParam(defaultValue = "true") Boolean ascending){

        return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS, clientService.findAll(active, sortir, ascending));
    }

    @PostMapping(path = "/client/save")
    @ApiOperation(value = "")
    public WSResponse save(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody @Valid ClientDto clientDto){
        Client client = modelMapper.map(clientDto, Client.class);
        boolean newdata = client.getId()==null || client.getId()==0 ;
        client = clientService.save(client);
        return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS, client);
    }

    @GetMapping(path = "/client/delete/{id}")
    public WSResponse delete(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @PathVariable Long id){
        Client client = clientService.findById(id);
        clientService.delete(id);
        return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS);
    }


}
