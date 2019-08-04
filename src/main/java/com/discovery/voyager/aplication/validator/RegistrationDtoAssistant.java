package com.discovery.voyager.aplication.validator;

import com.discovery.voyager.aplication.constant.ConstantAplication;
import com.discovery.voyager.aplication.exception.PasswordInvallidPatternException;
import com.discovery.voyager.aplication.exception.RequiredFieldException;
import com.discovery.voyager.aplication.model.dto.form.RegistrationDTO;
import com.discovery.voyager.aplication.model.entity.OtpEmailConfirmation;
import com.discovery.voyager.aplication.model.entity.Profile;
import com.discovery.voyager.aplication.model.entity.Role;
import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.util.AplicationUtil;
import com.discovery.voyager.aplication.util.PasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

//        if (!AplicationUtil.patternCorrect(registration.getEmail(), ConstantAplication.EMAIL_REGEX)) {
//            throw new InvalidEmailException();
//        }
        if (registration.getPassword().matches(ConstantAplication.PASSWORD_REGEX) || registration.getConfirmPassword().matches(ConstantAplication.PASSWORD_REGEX)) {
            throw new PasswordInvallidPatternException();
        }
    }

    public static User convertToEntity(RegistrationDTO registration, Role role, String pass) {
        User newUser =  new User();
        Profile newProfile = new Profile();
        newProfile.setFirstName(registration.getFirstName());
        newProfile.setLastName(registration.getLastName());
        newProfile.setEmail(registration.getEmail());
        newUser.setPassword(pass);
        newUser.setUsername(registration.getUsername());
        newUser.setProfile(newProfile);
        newUser.getRoles().add(role);
        newUser.setStatus(ConstantAplication.STATUS_PA);
        newUser.setOtpEmailConfirmation(generateOtpEmail());
        return newUser;
    }

    public static OtpEmailConfirmation generateOtpEmail(){
        OtpEmailConfirmation output = new OtpEmailConfirmation();
        output.setOtpSending(PasswordUtil.generatePassword(6));
        output.setOtpValidated(false);
        return output;
    }




}