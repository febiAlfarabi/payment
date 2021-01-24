package com.numeralasia.payment.util;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

public class MSRestTemplate extends RestTemplate {
    Logger logger = LoggerFactory.getLogger(MSRestTemplate.class);
    Gson gson = new Gson();

    @Override
    public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, Class<T> responseType) throws RestClientException {
        logger.debug("HIT = {} ", requestEntity.getUrl().toString());
        requestEntity.getHeaders().entrySet().forEach(stringListEntry -> {
            logger.debug("HIT Header name : {} #### Header value : {} ", stringListEntry.getKey(), stringListEntry.getValue());
        });
        logger.debug("HIT WITH BODY = {} ", gson.toJson(requestEntity.getBody()));
        ResponseEntity responseEntity =  super.exchange(requestEntity, responseType);
        logger.debug("RESPONSE = {}", responseEntity.toString());
        return responseEntity;
    }

    @Override
    public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, ParameterizedTypeReference<T> responseType) throws RestClientException {
        logger.debug("HIT :: {} ", requestEntity.getUrl().toString());
        requestEntity.getHeaders().entrySet().forEach(stringListEntry -> {
            logger.debug("HIT Header name : {} #### Header value : {} ", stringListEntry.getKey(), stringListEntry.getValue());
        });
        logger.debug("HIT WITH BODY = {} ", gson.toJson(requestEntity.getBody()));
        ResponseEntity responseEntity =  super.exchange(requestEntity, responseType);
        logger.debug("RESPONSE = {}", responseEntity.toString());
        return responseEntity;
    }

    @Override
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType) throws RestClientException {
        logger.debug("HIT :: {} ", url.toString());
        requestEntity.getHeaders().entrySet().forEach(stringListEntry -> {
            logger.debug("HIT Header name : {} #### Header value : {} ", stringListEntry.getKey(), stringListEntry.getValue());
        });
        logger.debug("HIT WITH BODY = {} ", gson.toJson(requestEntity.getBody()));
        ResponseEntity responseEntity =  super.exchange(url, method, requestEntity, responseType);
        logger.debug("RESPONSE = {}", responseEntity.toString());
        return responseEntity;
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) throws RestClientException {
        logger.debug("HIT :: {} ", url.toString());
        requestEntity.getHeaders().entrySet().forEach(stringListEntry -> {
            logger.debug("HIT Header name : {} #### Header value : {} ", stringListEntry.getKey(), stringListEntry.getValue());
        });
        logger.debug("HIT WITH BODY = {} ", gson.toJson(requestEntity.getBody()));
        ResponseEntity responseEntity =  super.exchange(url, method, requestEntity, responseType, uriVariables);
        logger.debug("RESPONSE = {}", responseEntity.toString());
        return responseEntity;
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        logger.debug("HIT :: {} ", url.toString());
        requestEntity.getHeaders().entrySet().forEach(stringListEntry -> {
            logger.debug("HIT Header name : {} #### Header value : {} ", stringListEntry.getKey(), stringListEntry.getValue());
        });
        logger.debug("HIT WITH BODY = {} ", gson.toJson(requestEntity.getBody()));
        ResponseEntity responseEntity =  super.exchange(url, method, requestEntity, responseType, uriVariables);
        logger.debug("RESPONSE = {}", responseEntity.toString());
        return responseEntity;
    }

    @Override
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType) throws RestClientException {
        logger.debug("HIT :: {} ", url.toString());
        requestEntity.getHeaders().entrySet().forEach(stringListEntry -> {
            logger.debug("HIT Header name : {} #### Header value : {} ", stringListEntry.getKey(), stringListEntry.getValue());
        });
        logger.debug("HIT WITH BODY = {} ", gson.toJson(requestEntity.getBody()));
        ResponseEntity responseEntity =  super.exchange(url, method, requestEntity, responseType);
        logger.debug("RESPONSE = {}", responseEntity.toString());
        return responseEntity;
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Object... uriVariables) throws RestClientException {
        logger.debug("HIT :: {} ", url.toString());
        requestEntity.getHeaders().entrySet().forEach(stringListEntry -> {
            logger.debug("HIT Header name : {} #### Header value : {} ", stringListEntry.getKey(), stringListEntry.getValue());
        });
        logger.debug("HIT WITH BODY = {} ", gson.toJson(requestEntity.getBody()));
        ResponseEntity responseEntity = super.exchange(url, method, requestEntity, responseType, uriVariables);
        logger.debug("RESPONSE = {}", responseEntity.toString());
        return responseEntity;
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        logger.debug("HIT :: {} ", url.toString());
        requestEntity.getHeaders().entrySet().forEach(stringListEntry -> {
            logger.debug("HIT Header name : {} #### Header value : {} ", stringListEntry.getKey(), stringListEntry.getValue());
        });
        logger.debug("HIT WITH BODY = {} ", gson.toJson(requestEntity.getBody()));
        ResponseEntity responseEntity = super.exchange(url, method, requestEntity, responseType, uriVariables);
        logger.debug("RESPONSE = {}", responseEntity.toString());
        return responseEntity;

    }

}
