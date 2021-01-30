package com.numeralasia.payment.model.util;

public class Constant {



    public static final String REFERENCE = "reference";
    public static final String AUTHORIZATION = "Authorization";
    public static final String FILENAME = "filename";

    public static final String CLASS_DOMAIN = "domain";
    public static final String CLASS_SUB_DOMAIN = "subdomain";
    public static final String PERSONAL = "personal";
    public static final String CORPORATE = "corporate";


    public static String prefixApi = "";

    public static final String API_DATE_FORMAT = "yyyy-MM-dd";
    public static final String API_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String API_DATE_EXAMPLE = "2000-12-27 22:59:59";

    public static final String dateFormat = "yyyy-MM-dd";
    public static final String DATE_TIMEZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String EXCEL_M_D_YY_H_MM = "M/d/yy h:mm";


    public static final String ALL = "ALL";
    public static final String ACTIVE = "ACTIVE";
    public static final String EXPIRED = "EXPIRED";


    public static final String AVAILABLE_DISCOUNT_DATE  = ALL+","+ACTIVE+","+EXPIRED;

    public static final Long SYSTEM_CUSTOMER = 1l;
    public static final Long SYSTEM_REGISTER = 1l;
    public static final String MASTER_PASSWORD = "Ecommerce%Master" ;


    public static final int SUCCESS_CODE = 200 ;
    public static final int FAILED_CODE = 300 ;
    public static final int VERIFICATION_FAILED_CODE = 302 ;
    public static final int ALREADY_VERIFIED_CODE = 303 ;
    public static final int JWT_SESSION_EXPIRED_CODE = 301 ;
    public static final int IMAGE_NOT_FOUND = 301 ;
    public static final int SMS_VERIFICATION_REACH_LIMIT = 301 ;
    public static final String SUCCESS = "SUCCESS" ;
    public static final String FAILED = "FAILED" ;

//    public static final String EMAIL_PATTERN = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    public static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String ID_PATTERN = "[0-9]+";
    public static final String ALPHABET_PATTERN = "^[a-zA-Z]*$";
    public static final String ALPHABET_LOWER_PATTERN = "^[a-z]*$";
    public static final String ALPHABET_SPACE_PATTERN = "^(?![ ]+$)[a-zA-Z ]*$";
    public static final String NUMERIC_PATTERN = "^[0-9]*$";

    public static final String FORM_MEDIA_DIRECTORY = "/media/kerjayuk/form";


//    public static final String REST_IMAGE_PHOTO_PROFILE = "image/photoProfile";
//    public static final String REST_IMAGE_IDCARD = "image/idcard";
//    public static final String REST_IMAGE_TRANSFER_BILL = "image/transferBill";

    public static final String MASTER_CUSTOMER_STATUS_JSON_FILE = "json/customer-status.json";
    public static final String MASTER_ADMIN_STATUS_JSON_FILE = "json/admin-status.json";
    public static final String MASTER_TRANSACTION_STATUS_JSON_FILE = "json/transaction-status.json";
    public static final String MASTER_GENDER_JSON_FILE = "json/gender.json";
    public static final String MASTER_ADMIN_JSON_FILE = "json/admin.json";
    public static final String MASTER_COMPANY_JSON_FILE = "json/company.json";
    public static final String MASTER_MENU_JSON_FILE = "json/menu.json";
    public static final String MASTER_ROLE_JSON_FILE = "json/role.json";
    public static final String MASTER_ROLE_MENU_JSON_FILE = "json/role-menu.json";
    public static final String MASTER_SIZE_JSON_FILE = "json/size.json";
    public static final String MASTER_COLOR_JSON_FILE = "json/color.json";
    public static final String MASTER_PAYMENT_MEDIA_JSON_FILE = "json/payment_media.json";



    public static final String REST_USER_PHOTO_PROFILE = "/image/user/photoProfile";
    public static final String REST_ADMIN_PHOTO_PROFILE = "/image/admin/photoProfile";
    public static final String REST_BANNER_IMAGE = "/image/banner";
    public static final String REST_BRANCH_IMAGE = "/image/branch";
    public static final String REST_CUSTOMER_GUIDE_IMAGE = "/image/customerGuide";
    public static final String REST_SELEBGRAM_IMAGE = "/image/selebgram";
    public static final String REST_PRODUCT_IMAGE = "/image/product";
    public static final String REST_MAIN_PRODUCT_IMAGE = "/image/mainProduct";
    public static final String REST_INVENTORY_IMAGE = "/image/inventory";
    public static final String REST_IDCARD_IMAGE = "/image/idcard";
    public static final String REST_PAYMENT_IMAGE = "/image/payment";
    public static final String REST_PAYMENT_MEDIA_IMAGE = "/image/paymentMedia";
    public static final String REST_BANK_IMAGE = "/image/bank";
    public static final String REST_BRAND_IMAGE = "/image/brand";
    public static final String REST_ONLINE_SHOP_IMAGE = "/image/onlineShop";
    public static final String REST_MIDTRANS_MEDIATOR_IMAGE = "/image/midtransMediator";
    public static final String REST_COURIER_IMAGE = "/image/courier";

    public static final String REST_COMPANY_IMAGE = "/image/company/image";
    public static final String REST_COMPANY_THUMBNAIL = "/image/company/thumbnail";

    public static final String REST_EMAIL_HTML_FILE = "/file/email/html";
    public static final String REST_DOWNLOAD_PAYMENT_FILE = "/file/payment/download";
    public static final String REST_EMAIL_ATTACHMENT_FILE = "/file/email/attachment";
    public static final String REST_EMAIL_IMAGE_FILE = "/image/email/image";
    public static final String REST_FAQ_IMAGE = "/image/faq";
    public static final String REST_USER_PHOTO = "/image/user/photo";
    public static final String REST_EMPLOYEE_ID_CARD = "/image/employee/idcard";
    public static final String REST_PRODUCT_ICON = "/image/product";
    public static final String REST_CUSTOMER_BANNER = "/image/customer/banner";
    public static final String REST_CONFIGURATION_WORKING_AGREEMENT_DOCUMENT = "/image/configuration/workingAgreement";
    public static final String REST_TEMPORARY_IMAGE = "/image/temporary";
    public static final String REST_USER_ID_CARD = "/image/user/idCard";
    public static final String REST_USER_SELFIE_ID_CARD = "/image/user/selfieIdCard";
    public static final String REST_TASK_BANNER_IMAGE = "/image/task/banner";
    public static final String REST_PARTNER_IMAGE = "/image/partner/image";
    public static final String REST_PARTNER_SIUP_OR_NPWP = "/image/partner/siupOrNpwp";
    public static final String REST_PARTNER_ID_CARD = "/image/partner/idcard";
    public static final String REST_WITHDRAWAL_RECEIPT_IMAGE = "/image/withdrawal/receipt";
    public static final String REST_TEMPLATE_IMAGE = "/image/template";
    public static final String REST_TICKET_IMAGE = "/image/ticket";




    public static final Integer PRODUCTION = 1;
    public static final Integer DEVELOPMENT = 0;


    public static final Integer BUILD_MODE = DEVELOPMENT;

//    public static final String PASSWORD_ENCRYPTION_KEY = "InsyaAllahBermanfaatUntukOrangLain";

    public static final String CLIENT_HEADER_KEY = "InsyaAllahBermanfaatUntukOrangLain";


    public static final String ROLE = "role";


    public static final String RESET_PASSWORD_TML = "reset_password";
    public static final String RESET_PASSWORD_SUCCESS_TML = "reset_password_success";

    public static final float VERY_LOW_QUALITY = 0.2f;
    public static final float MEDIUM_QUALITY = 0.35f;
    public static final float HIGH_QUALITY = 0.7f;

    public static final String CACHE_CONFIGURATION = "configuration";

    public static final String CACHE_CUSTOMER_ID = "customerId";
    public static final String CACHE_USER_PRODUCTS = "userProducts";
    public static final String CACHE_USER_DASHBOARD = "userDashboard";
    public static final String CACHE_BANNERS = "banners";
    public static final String CACHE_HEADINGS = "headings";
    public static final String CACHE_SELEBGRAMS = "selebgrams";
    public static final String CACHE_ONLINE_SHOPS = "onlineShops";


    public static final String CURRENCY_SYMBOL = "Rp ";

    public static final String USER = "user";
    public static final String ADMIN = "admin";
    public static final String CUSTOMER = "customer";
    public static final String SWAGGER = "swagger";

    public static final String DELETED_SUCCESSFULLY = "DELETED_SUCCESSFULLY" ;

    public static final String PHOTO = "photo";
    public static final String ID_CARD = "id_card";
    public static final String SELFIE_ID_CARD = "selfie_id_card";

    public static final String IMAGE_UPLOAD_TYPE = PHOTO+", "+ID_CARD+", "+SELFIE_ID_CARD;


    public static final String MASTER_USER_JSON_FILE = "json/users.json";

    public static final String AVENGER_KEY = "TindakanPenyusupanAdalahPelanggaran,DanKamiAkanMelaporkanTPelanggaranSecepatnyaKepadaPihakYangBerwajib!!)(*&^%$#@!";



}
