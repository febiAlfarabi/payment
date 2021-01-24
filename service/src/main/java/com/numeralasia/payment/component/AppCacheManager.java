package com.numeralasia.payment.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.numeralasia.payment.model.exception.AppException;
import com.numeralasia.payment.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppCacheManager {

    public static final Logger logger = LoggerFactory.getLogger(AppCacheManager.class);

    @Autowired CacheManager cacheManager;
    @Autowired Gson gson;


    public void evictSingleCacheValue(String cacheName, Object cacheKey) {
        cacheManager.getCache(cacheName).evict(cacheKey);
    }

    public void evictAllCacheValues(String cacheName) {
        cacheManager.getCache(cacheName).clear();
    }

    public void evictCacheByName(String... cacheNames){
        if(cacheNames!=null && cacheNames.length>0){
            for (String cacheName : cacheNames){
                cacheManager.getCache(cacheName).clear();
            }
        }
    }

    public void evictCacheByNameAndKey(String cacheName, Object key){
        try{
            cacheManager.getCache(cacheName).evict(key);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void evictAll(){
        if(cacheManager.getCacheNames()!=null){
            for (String name :cacheManager.getCacheNames()){
                evictAllCacheValues(name);
            }
        }
    }

    public boolean exist(String key){
        return cacheManager.getCache(key)!=null;
    }


    public <T> T cacheValue(String name, String key, Class<T> claxx){
        Cache cache =  cacheManager.getCache(name) ;
        try{
            if(cache!=null && cache.getNativeCache()!=null){
                ObjectMapper objectMapper = new ObjectMapper();
                Map map= objectMapper.convertValue(cache.getNativeCache(), Map.class);
                if(map.get(key)!=null){
                    return objectMapper.convertValue(map.get(key), claxx);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        throw new AppException(Constant.FAILED_CODE, name+" is not found");
    }





//    @CachePut(value = "password", key = "#id")
//    public String putPassword(String password, Long id){
//        return password ;
//    }
//
//    @Cacheable(value = "password", key = "#id")
//    public String getPassword(Long id){
//        return cacheManager.getCache("password").get(id).get().toString();
//    }



}
