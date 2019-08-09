package com.discovery.voyager.aplication.validator;

import com.discovery.voyager.aplication.constant.ConstantApplication;
import com.discovery.voyager.aplication.exception.PasswordInvallidPatternException;
import com.discovery.voyager.aplication.exception.RequiredFieldException;
import com.discovery.voyager.aplication.model.dto.form.RegistrationDTO;
import com.discovery.voyager.aplication.model.entity.OtpEmailConfirmation;
import com.discovery.voyager.aplication.model.entity.Profile;
import com.discovery.voyager.aplication.model.entity.Role;
import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.util.AplicationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Date;

public class RegistrationDtoAssistant {

    private static Logger log = LogManager.getLogger(RegistrationDtoAssistant.class);

    public static void validationRequiredField(RegistrationDTO registration) throws RequiredFieldException {
        log.debug("entering method validationRequiredField");
        log.debug("param : " + registration);
        if (!AplicationUtil.isStringNullOrEmpty(registration.getPassword()) || AplicationUtil.isStringNullOrEmpty(registration.getUsername()) ||
                AplicationUtil.isStringNullOrEmpty(registration.getConfirmPassword())) {
            throw new RequiredFieldException();
        }
        if (!AplicationUtil.isStringNullOrEmpty(registration.getFirstName()) ||
                AplicationUtil.isStringNullOrEmpty(registration.getLastName()) ||
                AplicationUtil.isStringNullOrEmpty(registration.getEmail())) {
            throw new RequiredFieldException();
        }
    }

    public static void validationIntegrityPasswordField(RegistrationDTO registration) throws PasswordInvallidPatternException{
        if (registration.getPassword().matches(ConstantApplication.PASSWORD_REGEX) || registration.getConfirmPassword().matches(ConstantApplication.PASSWORD_REGEX)) {
            throw new PasswordInvallidPatternException();
        }
    }

    public static User convertToEntity(RegistrationDTO registration, Role role, String pass, String sentOtp, boolean emailSend) {
        User newUser =  new User();
        Profile newProfile = new Profile();
        newProfile.setFirstName(registration.getFirstName());
        newProfile.setLastName(registration.getLastName());
        newProfile.setEmail(registration.getEmail());
        newUser.setPassword(pass);
        newUser.setUsername(registration.getUsername());
        newUser.setProfile(newProfile);
        newUser.setRole(role);
        newUser.setStatus(ConstantApplication.STATUS_PA);
        newUser.setOtpEmailConfirmation(generateOtpEmail(sentOtp, emailSend));
        newUser.setCreationDate(new Date());
        return newUser;
    }

    public static OtpEmailConfirmation generateOtpEmail(String otp, boolean sendEmail){
        OtpEmailConfirmation output = new OtpEmailConfirmation();
        output.setOtpEmailSend(sendEmail);
        output.setOtpSending(otp);
        output.setOtpValidated(false);
        output.setOtpSendingDate(new Date());
        return output;
    }
}