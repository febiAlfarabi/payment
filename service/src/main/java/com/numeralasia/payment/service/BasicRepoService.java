package com.numeralasia.payment.service;

import com.google.gson.Gson;
import com.numeralasia.payment.component.AppCacheManager;
import com.numeralasia.payment.component.FileStorage;
import com.numeralasia.payment.entity.BasicField;
import com.numeralasia.payment.model.exception.AppException;
import com.numeralasia.payment.util.Constant;
import com.numeralasia.payment.util.MSRestTemplate;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.Query;
import java.util.*;

@MappedSuperclass
public abstract class BasicRepoService<T extends BasicField> {

    private static final Logger logger = LoggerFactory.getLogger(BasicRepoService.class.getName());

    @Autowired protected MessageSource messageSource;
    @Autowired protected ModelMapper mapper;
    @Autowired protected MSRestTemplate msRestTemplate;
    @Autowired protected FileStorage fileStorage;
    @Autowired protected EntityManager entityManager ;
    @Autowired protected AppCacheManager appCacheManager ;

    public static EntityManager staticEntityManager ;

    @PostConstruct
    public void construct(){
        BasicRepoService.staticEntityManager = entityManager ;
    }

    @Value("${page.row}")
    protected int pageRow ;

    @Autowired
    protected Gson gson ;

    public Boolean existsById(Long id){
        return repository().existsById(id);
    }

    public List<T> findAll() {
        return repository().findAll();
    }

    public List<T> findAll(Sort sort) {
        return repository().findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository().findAll(pageable);
    }

    public T findById(Long id) {
        Optional<T> optionalT = repository().findById(id);
        if(!optionalT.isPresent()){
            throw new AppException(Constant.FAILED_CODE, message("not.found.on.the.system", "id"));
        }
        return optionalT.get();
    }

    public void delete(Long id) {
        repository().deleteById(id);
    }

    @Transactional
    public T save(T object){
        object.setUpdated(new Date());
        object = (T) repository().save(object);
        object.initTransient();
        return object ;
    }

    public <O extends BasicField> void checkForDelete(Set<O> newList, Set<O> previousList){
        if(newList==null || newList.size()==0){
            if(previousList!=null){
                previousList.forEach(o -> {
                    try{
                        delete(o.getId());
                    }catch (Exception e){
                    }
                });

            }
            return;
        }
        if(previousList!=null){
            previousList.forEach(o -> {
                boolean found = false ;
                for (O o1 : newList) {
                    if(o1.getId()!=null && o.getId()!=null && o1.getId().equals(o.getId()) || o1.getId()==o.getId()){
                        found = true ;
                    }
                }
                if(!found){
                    try{
                        delete(o.getId());
                    }catch (Exception e){
                    }
                }
            });
        }
    }

    protected String message(String message, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(message, args==null?new Object[]{}:args, locale);
    }

    public abstract <JPA extends JpaRepository> JPA repository();


    protected long resultCount(Integer page, String sql, Map<String, Object> paramaterMap){
        int offset = page*pageRow;
        long countResult = 0 ;

        try {
            Query queryTotal = entityManager.createQuery(sql);
            for(String key :paramaterMap.keySet()) {
                queryTotal.setParameter(key, paramaterMap.get(key));
            }
            countResult = (long) queryTotal.getResultList().size();
        }catch (Exception e){
            e.printStackTrace();
            countResult = 0 ;
        }
        return countResult ;
    }


}
