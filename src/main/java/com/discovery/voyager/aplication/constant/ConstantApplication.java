package com.discovery.voyager.aplication.constant;

public interface ConstantApplication {

    String ANONYMOUS_USER="anonymousUser";

    String ROLE_USER="ROLE_USER";
    String ROLE_ADMIN="ROLE_ADMIN";
    String ROLE_GUEST="ROLE_GUEST";
    String ROLE_MANAGEMENT="ROLE_MANAGEMENT";

    String ROLE_MANAGEMENT_PREFIX="/management";
    String ROLE_USER_PREFIX="/user";
    String ROLE_ADMIN_PREFIX="/admin";
    String ROLE_GUEST_PREFIX="/guest";

    String STATUS_PA="PA";
    String STATUS_IN="IN";
    String STATUS_A="A";

    //erroro code 
    long INVALID_USER_ERROR_CODE = 501;
    long INVALID_MATCH_PASSWORD_ERROR_CODE = 502;
    long INVALID_MATCH_OTP_ERROR_CODE = 504;

    long DUPLICATE_USERNAME_ERROR_CODE = 503;

    long REQUIRED_FIELD_ERROR_CODE = 800;
    long PASSWORD_INVALID_PATTERN_ERROR_CODE = 801;
    long EMAIL_INVALID_PATTERN_ERROR_CODE = 801;

    //email template code
    String REGISTRATION_EMAIL_CODE= "Email_new_registration";
    String UPDATE_USER_PROFILE= "Update_user_profile";

    //error message
    String FORM_BLANK_VALUE= "There is a required attribute not provided";

    String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    String PASSWORD_REGEX = "\\A(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\\z"; // false, if not is valid regex

}