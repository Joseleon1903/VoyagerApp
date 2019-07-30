
package com.discovery.voyager.aplication.constant;

public interface ConstantAplication{

    public String ANONYMOUS_USER="anonymousUser";

    //erroro code 
    public static long INVALID_USER_ERROR_CODE = 501;
    public static long INVALID_MATCH_PASSWORD_ERROR_CODE = 502;
    public static long DUPLICATE_USERNAME_ERROR_CODE = 503;

    //email template code
    public static String REGISTRATION_EMAIL_CODE= "Email_new_registration";
    public static String UPDATE_USER_PROFILE= "Update_user_profile";

    //error message
    public static String FORM_BLANK_VALUE= "There is a required attribute not provided";

}