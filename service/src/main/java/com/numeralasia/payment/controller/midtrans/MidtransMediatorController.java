package com.numeralasia.payment.controller.midtrans;

import com.numeralasia.payment.controller.BasicController;
import com.numeralasia.payment.entity.client.Client;
import com.numeralasia.payment.entity.midtrans.MidtransMediator;
import com.numeralasia.payment.model.WSResponse;
import com.numeralasia.payment.model.midtrans.MidtransFeeDto;
import com.numeralasia.payment.model.midtrans.MidtransMediatorDto;
import com.numeralasia.payment.service.client.ClientService;
import com.numeralasia.payment.service.midtrans.MidtransMediatorService;
import com.numeralasia.payment.model.util.Constant;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.util.List;

@RestController
@RequestMapping(path = "${api}")
public class MidtransMediatorController extends BasicController {

    @Autowired MidtransMediatorService midtransMediatorService;
    @Autowired ClientService clientService ;

    @Value("${corporate.mode}") Boolean corporateMode ;
    @Value("${personal.mode}") Boolean personalMode ;


    @GetMapping(path = "/midtransMediators")
    @ApiOperation(value = "")
    public WSResponse findAll(
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "name") String sortir,
            @RequestParam(defaultValue = "true") Boolean ascending){

        return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS, midtransMediatorService.findAll(null, null, active, sortir, ascending));
    }

    @GetMapping(path = "/midtransMediator/available")
    @ApiOperation(value = "")
    public WSResponse available(
            @RequestParam(required = false) String companyClass,
            @RequestParam(required = false) String domainClass,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "name") String sortir,
            @RequestParam(defaultValue = "true") Boolean ascending){
        if(StringUtils.equalsIgnoreCase(domainClass, Constant.CLASS_SUB_DOMAIN)){
            return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS, midtransMediatorService.findAll(corporateMode, personalMode, active, sortir, ascending));
        }
        if(StringUtils.equalsIgnoreCase(domainClass, Constant.CLASS_DOMAIN)){
            if(StringUtils.equalsIgnoreCase(companyClass, Constant.CORPORATE)){
                return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS, midtransMediatorService.findAll(true, false, active, sortir, ascending));
            }
        }
        return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS, midtransMediatorService.findAll(corporateMode, personalMode, active, sortir, ascending));
    }


    @PostMapping(path = "/midtransMediator/save")
    @ApiOperation(value = "")
    public WSResponse save(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody @Valid MidtransFeeDto midtransMediatorDto){
        MidtransMediator midtransMediator = modelMapper.map(midtransMediatorDto, MidtransMediator.class);
        boolean newdata = midtransMediator.getId()==null || midtransMediator.getId()==0 ;
        midtransMediator = midtransMediatorService.save(midtransMediator);
        if(midtransMediator.getActive()){
            publishSave(midtransMediator);
        }else{
            publishDelete(midtransMediator);
        }
        return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS, midtransMediator);
    }

    @PostMapping(path = "/midtransMediator/saveUpload")
    public WSResponse saveUpload(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestPart String midtransMediatorDtoGson, @RequestParam(required = false) MultipartFile multipartFile) throws Exception{
        if(multipartFile!=null){
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            Integer width = image.getWidth();
            Integer height = image.getHeight();
        }
        MidtransMediatorDto midtransMediatorDto = gson.fromJson(midtransMediatorDtoGson, MidtransMediatorDto.class);
        MidtransMediator midtransMediator = modelMapper.map(midtransMediatorDto, MidtransMediator.class);

        boolean newdata = midtransMediator.getId()==null || midtransMediator.getId()==0 ;

        midtransMediator = midtransMediatorService.save(midtransMediator);
        if(multipartFile!=null){
            String filename = String.valueOf(System.currentTimeMillis());
            String image = fileStorage.storeFile(midtransMediatorImageDir, String.valueOf(midtransMediator.getId()), filename, multipartFile);
            midtransMediator.setImage(image);
            midtransMediator = midtransMediatorService.save(midtransMediator);
        }
        if(midtransMediator.getActive()){
            publishSave(midtransMediator);
        }else{
            publishDelete(midtransMediator);
        }
        return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS, midtransMediator);
    }

    @PostMapping(path = "/midtransMediator/uploadImage")
    public WSResponse uploadImage(@RequestParam Long midtransMediatorId, @RequestParam MultipartFile multipartFile) throws Exception{
        MidtransMediator midtransMediator = midtransMediatorService.findById(midtransMediatorId);
        if(multipartFile!=null){
            String filename = String.valueOf(System.currentTimeMillis());
            String image = fileStorage.storeFile(midtransMediatorImageDir, String.valueOf(midtransMediator.getId()), filename, multipartFile);
            midtransMediator.setImage(image);
            midtransMediator = midtransMediatorService.save(midtransMediator);
            if(midtransMediator.getActive()){
                publishSave(midtransMediator);
            }else{
                publishDelete(midtransMediator);
            }
            return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS, midtransMediator.getImageLink());
        }
        return WSResponse.instance(Constant.FAILED_CODE, Constant.FAILED);
    }

    @GetMapping(path = "/midtransMediator/delete/{id}")
    public WSResponse delete(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @PathVariable Long id){
        MidtransMediator midtransMediator = midtransMediatorService.findById(id);
        midtransMediatorService.delete(id);
        publishDelete(midtransMediator);
        return WSResponse.instance(Constant.SUCCESS_CODE, Constant.SUCCESS);
    }

    private void publishSave(MidtransMediator midtransMediator){
        List<Client> clients = clientService.findAll();
        for (Client client:clients) {
            if(client.getDomainClass().equalsIgnoreCase(Constant.CLASS_SUB_DOMAIN)){
                if(BooleanUtils.isTrue(corporateMode)){
                    if(BooleanUtils.isTrue(midtransMediator.getCorporateAvailable())){
                        publishRest(client, midtransMediator);
                    }else{
                        deleteRest(client, midtransMediator);
                    }
                }
                if(BooleanUtils.isTrue(personalMode)){
                    if(BooleanUtils.isTrue(midtransMediator.getPersonalAvailable())){
                        publishRest(client, midtransMediator);
                    }else{
                        deleteRest(client, midtransMediator);
                    }
                }
            }else{
                if(client.getCompanyClass().equalsIgnoreCase(Constant.CORPORATE)){
                    if(BooleanUtils.isTrue(midtransMediator.getCorporateAvailable())){
                        publishRest(client, midtransMediator);
                    }else{
                        deleteRest(client, midtransMediator);
                    }
                }
                if(client.getCompanyClass().equalsIgnoreCase(Constant.PERSONAL)){
                    if(BooleanUtils.isTrue(midtransMediator.getPersonalAvailable())){
                        publishRest(client, midtransMediator);
                    }else{
                        deleteRest(client, midtransMediator);
                    }
                }
            }
        }
    }

    private void publishRest(Client client, MidtransMediator midtransMediator){
        String fullDomain = client.getServiceAddress()+"/api/midtransMediator/sync/save";
        try{
            ResponseEntity<WSResponse> wsResponse = msRestTemplate.postForEntity(fullDomain, midtransMediator, WSResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void publishDelete(MidtransMediator midtransMediator){
        List<Client> clients = clientService.findAll();
        for (Client client:clients) {
            deleteRest(client, midtransMediator);
        }
    }
    private void deleteRest(Client client, MidtransMediator midtransMediator){
        String fullDomain = client.getServiceAddress()+"/api/midtransMediator/sync/delete/"+midtransMediator.getPaymentType();
        try{
            ResponseEntity<WSResponse> wsResponse = msRestTemplate.getForEntity(fullDomain, WSResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
