
package com.discovery.voyager.aplication.constant;

public interface ConstantAplication{

    String ANONYMOUS_USER="anonymousUser";

    String ROLE_USER="ROLE_USER";
    String ROLE_ADMIN="ROLE_ADMIN";
    String ROLE_GUEST="ROLE_GUEST";



    String STATUS_PA="PA";
    String STATUS_IN="IN";
    String STATUS_A="A";


    //erroro code 
    long INVALID_USER_ERROR_CODE = 501;
    long INVALID_MATCH_PASSWORD_ERROR_CODE = 502;
    long DUPLICATE_USERNAME_ERROR_CODE = 503;

    long REQUIRED_FIELD_ERROR_CODE = 800;

    //email template code
    String REGISTRATION_EMAIL_CODE= "Email_new_registration";
    String UPDATE_USER_PROFILE= "Update_user_profile";

    //error message
    String FORM_BLANK_VALUE= "There is a required attribute not provided";

}