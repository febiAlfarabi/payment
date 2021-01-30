package com.numeralasia.payment.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.febialfarabi.utility.MSRestTemplate;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.util.ContentCachingRequestWrapper;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Locale;

//import org.springframework.cache.gu;

@Configuration
@EnableSwagger2
//@EnableWebMvc
public class BasicConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware{


    private static final Logger logger4j = LoggerFactory.getLogger(BasicConfiguration.class);

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true).
        favorParameter(false).useJaf(false).ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Bean
    public Docket universalApi() {
        return new Docket(DocumentationType.SWAGGER_12)
                .groupName("1 - Universal Documentation")
                .select()
                .paths(PathSelectors.any())
                .build().apiInfo(
                        new ApiInfoBuilder()
                                .title("Payment Universal Documentation")
                                .description("API Documentation with json structure / formatting," +
                                        "Rest Method, e.t.c for Payment")
                                .license("Apache 2.0")
                                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                                .termsOfServiceUrl("")
                                .version("1.0.0")
                                .contact(new Contact("", "", "support@numeralasia.com"))
                                .build()
                );
    }


    @Bean
    Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(requestInterceptor).addPathPatterns("/**/**/**/");
            }
        };
    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*");
//    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:messages/messages",
                "classpath:messages/notification",
                "classpath:messages/kerjayuk-notification",
                "classpath:messages/user-activity-label",
                "classpath:messages/admin-activity-label",
                "classpath:messages/user-activity",
                "classpath:messages/admin-activity"
        );
        // If true, the key of the message will be displayed if the key is not found, instead of throwing an exception
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        // The value 0 means always reload the messages to be developer friendly
        messageSource.setCacheSeconds(0);
        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if (!registry.hasMappingForPattern("/static/**")) {
//            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        }
        registry.addResourceHandler(
                "/webjars/**",
                "/color/**",
                "/contactform/**",
                "/css/**",
                "/fonts/**",
                "/fonts/fontawesome/**",
                "/ico/**",
                "/img/**",
                "/js/**",
                "/otf/**",
                "/eot/**",
                "/svg/**",
                "/ttf/**",
                "/**.xml",
                "/**.txt",
                "/json/**.json",
                "/**.json")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/color/",
                        "classpath:/static/contactform/",
                        "classpath:/static/css/",
                        "classpath:/static/fonts/fontawesome/",
                        "classpath:/static/ico/",
                        "classpath:/static/image/",
                        "classpath:/static/js/",
                        "classpath:/templates/",
                        "classpath:/static/",
                        "classpath:/json/");
    }



    @Bean
    public LocaleResolver localeResolver() {
        return new SmartLocaleResolver();
    }



    public class SmartLocaleResolver extends CookieLocaleResolver {

        @Override
        public Locale resolveLocale(HttpServletRequest httpServletRequest) {
            HttpServletRequest request = new ContentCachingRequestWrapper(httpServletRequest);

            for (String httpHeaderName : Collections.list(request.getHeaderNames())) {
                logger4j.debug("===========>> Header name: " + httpHeaderName);
            }
            String acceptLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
            logger4j.debug("===========>> acceptLanguage: " + acceptLanguage);
            Locale locale = super.resolveLocale(request);
            logger4j.debug("===========>> acceptLanguage locale: " + locale.getDisplayCountry());
            if (null == locale) {
                locale = getDefaultLocale();
                logger4j.debug("===========>> Default locale: " + locale.getDisplayCountry());
            }
            return locale;
        }

    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        return mapper;
    }

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource);
        return validatorFactoryBean;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MSRestTemplate msRestTemplate(){
        return new MSRestTemplate();
    }



}
