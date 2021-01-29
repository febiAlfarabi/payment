package com.numeralasia.payment.controller;

import com.google.gson.Gson;
import com.numeralasia.payment.component.FileStorage;
import io.github.febialfarabi.utility.MSRestTemplate;
import io.swagger.annotations.ApiParam;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.persistence.MappedSuperclass;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@MappedSuperclass
public abstract class BasicController<E> {

    private static final Logger logger = LoggerFactory.getLogger(BasicController.class.getName());


    @Value("${temporary.dir}") protected String temporaryDir ;
    @Value("${midtrans.mediator.image.dir}") protected String midtransMediatorImageDir ;
    @Value("${template.image.dir}") protected String templateImageDir ;


//    @Value("${dr.strange.avenger.key}")
//    protected String drStrangeAvengerKey ;

    @Value("${admin.domain}") protected String adminDomain ;
    @Value("${account.domain}") protected String accountDomain ;
    @Value("${public.domain}") protected String publicDomain ;

//    @Autowired protected PasswordEncoder passwordEncoder ;
    @Autowired protected MSRestTemplate msRestTemplate ;
    @Autowired MessageSource messageSource ;
    @Autowired protected Gson gson ;
    @Autowired protected FileStorage fileStorage;

    @Autowired protected ModelMapper modelMapper ;
    @Autowired LocalValidatorFactoryBean validator ;
    @Autowired protected HttpServletRequest request ;


    @ApiParam protected ConfigurationService configurationService ;

    protected String message(String message, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(message, args==null?new Object[]{}:args, locale);
    }





}
