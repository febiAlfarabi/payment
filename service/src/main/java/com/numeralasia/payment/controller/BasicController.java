package com.numeralasia.payment.controller;

import com.google.gson.Gson;
import com.numeralasia.payment.component.FileStorage;
import com.numeralasia.payment.util.MSRestTemplate;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.QueryException;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.persistence.MappedSuperclass;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.io.File;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.*;

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





}
