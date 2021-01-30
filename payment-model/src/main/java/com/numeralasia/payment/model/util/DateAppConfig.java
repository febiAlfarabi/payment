package com.numeralasia.payment.model.util;

//@Configuration
public class DateAppConfig {

    private static final String dateFormat = "yyyy-MM-dd";
    private static final String COMPLETE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static final String API_DATE = "yyyy-MM-dd";
    public static final String PARAM_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String API_DATE_FORMAT = COMPLETE_DATE_FORMAT;
    public static final String RETROFIT_DATE_FORMAT = "MMM dd, yyyy HH:mm:ss a";
    public static final String BLOG_DATE_FORMAT ="yyyy-MM-dd'T'HH:mm:ss";
    public static final String API_DATE_EXAMPLE = "2000-12-27 22:59:59";

    public static final String DATETIME_VIEW_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String VIEW_DATE_FORMAT = "dd-MM-yyyy";


    public static final String VIEW_FULL_DATE_FORMAT = "dd MMMM yyyy, HH:mm";



//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//        return builder -> {
//            builder.simpleDateFormat(dateTimeFormat);
//            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
//            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
//            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
//            builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
//        };
//    }

}
