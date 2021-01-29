package com.numeralasia.payment.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.google.common.primitives.Primitives;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.*;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Utils {


    private static final Logger logger = LoggerFactory.getLogger(Utils.class.getName());

    public static String readResourceAsString(Resource resource) throws Exception{
        InputStream resourcee = resource.getInputStream();
        String text = null;
        try (final Reader reader = new InputStreamReader(resourcee)) {
            text = CharStreams.toString(reader);
        }
        return text ;
    }

    public static String readJsonFromResource(String fileInResource) throws Exception{
        File file = ResourceUtils.getFile("classpath:"+fileInResource);
        String content = new String(Files.readAllBytes(file.toPath()));
        logger.debug(content);
        return content ;
    }

    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }


    public static List<List<String>> readCsvFromResource(String fileInResource, boolean skipHeader){
        try {

            File file = ResourceUtils.getFile("classpath:"+fileInResource);
            List<List<String>> records = new ArrayList<>();
            int i = 0 ;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if(skipHeader && i==0){
                        // Do Nothing
                    }else{
                        line = line.replaceAll("\"", "");
                        String[] values = line.split(";");
                        records.add(Arrays.asList(values));
                    }
                    i++;
                }
                return records;
            }catch (Exception e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file " + fileInResource, e);
            return Collections.emptyList();
        }
        return new ArrayList<>() ;
    }

    public static Sort sort(Boolean asc, String sortir){
        return new Sort(asc? Sort.Direction.ASC: Sort.Direction.DESC, sortir);
    }

    public static <T> Page<T> paging(List<T> items, int page, int pageSizeLimit){
        Pageable pageable = PageRequest.of(page, pageSizeLimit);
        long total = pageable.getOffset()+pageable.getPageSize();
        long start = pageable.getOffset();
        long end = (start + pageable.getPageSize()) > items.size() ? items.size() : (start + pageable.getPageSize());
        return new PageImpl<>(items.subList((int)start, (int)end), pageable, items.size());

    }

    public static <T> T cast(Class<T> tClass, Object object){
        if(object==null){
            return null ;
        }
        if(object instanceof BigInteger){
            return Primitives.wrap(tClass).cast(((BigInteger)object).longValue());
        }
        return Primitives.wrap(tClass).cast(object);
    }
    public static String slugBuilder(String name){
        String slug = "";
        slug = StringUtils.lowerCase(name);
        slug = slug.trim().replaceAll("[^a-zA-Z0-9\\s]", "");
        slug = slug.trim().replaceAll(" ", "-");
        return slug ;
    }

    public static <T> T cast(Class<T> tClass, Object object, Object defaultValue){
        if(object==null){
            object = defaultValue ;
        }
        return Primitives.wrap(tClass).cast(object);
    }

    public static <T> Map toMap(T t){
        String json = new Gson().toJson(t);
        Type typeToken = new TypeToken<Map>(){}.getType();
        Map map = new Gson().fromJson(json, typeToken);
        return map ;
    }

    public static final String currencyFormat(BigDecimal amount){
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        return df.format(amount);
    }

    public static String[] splitName(String name){
        String fullname = name.trim();
        String firstname = "";
        String lastname = "";
        if(fullname.contains(" ")){
            String names[] = fullname.split(" ", 2);
            firstname = names[0];
            lastname = names[1];
        }else{
            firstname = name ;
        }

        return new String[]{firstname, lastname};
    }

    public static MessageSource messageSource = new MessageSource() {
        @Override
        public String getMessage(String s, Object[] objects, String s1, Locale locale) {
            return "";
        }

        @Override
        public String getMessage(String s, Object[] objects, Locale locale) throws NoSuchMessageException {
            return "";
        }

        @Override
        public String getMessage(MessageSourceResolvable messageSourceResolvable, Locale locale) throws NoSuchMessageException {
            return "";
        }
    };

    public static final String readFile(String path) throws Exception{
        InputStream is = new FileInputStream(path);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        while(line != null){
            sb.append(line).append("\n"); line = buf.readLine();
        }
        return  sb.toString();
    }


    public static void validateParam(String... inputs){
        boolean found = false ;
        if(inputs!=null){
            for (int i = 0; i < inputs.length; i++) {
                found = validateParam2(inputs[i]);
                if(found){
                    break;
                }
            }
        }
        if (found){
            throw new RuntimeException("There is a special character in paramater");
        }

    }


    public static final String ALLOWABLE_CHARACTERS = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm._ ";
    private static boolean validateParam2(String input){
        if (input == null || input.trim().isEmpty()) {
            return false ;
        }

        boolean failed = false ;
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            failed = (ALLOWABLE_CHARACTERS.indexOf(chars[i]) < 0);
            if(failed){
                break;
            }
        }

//        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
//        Matcher matcher = pattern.matcher(input);
//
//        return matcher.find();
        return failed ;
    }

    @SuppressWarnings("unchecked")
    public static <O extends E, E> O mergeInheritance(O first, E second, Class<O> claxx) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map1 = objectMapper.convertValue(first, Map.class);
        Map map2 = objectMapper.convertValue(second, Map.class);

        map1.putAll(map2);
        return objectMapper.convertValue(map1, claxx);
    }

    public static <O> O merge(O first, O second, Class<O> claxx) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map1 = objectMapper.convertValue(first, Map.class);
        Map map2 = objectMapper.convertValue(second, Map.class);

        map1.putAll(map2);
        return objectMapper.convertValue(map1, claxx);
    }


    public static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }


}
