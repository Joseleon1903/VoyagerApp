package com.discovery.voyager.aplication.controller.profile;

import com.discovery.voyager.aplication.constant.ConstantAplication;
import com.discovery.voyager.aplication.exception.PasswordInvallidPatternException;
import com.discovery.voyager.aplication.model.dto.form.OtpFormDto;
import com.discovery.voyager.aplication.model.dto.form.RegistrationDTO;
import com.discovery.voyager.aplication.model.entity.Role;
import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.repository.RoleRepository;
import com.discovery.voyager.aplication.service.implementation.EmailServiceImpl;
import com.discovery.voyager.aplication.service.implementation.ErrorExceptionService;
import com.discovery.voyager.aplication.service.implementation.UserServiceImpl;
import com.discovery.voyager.aplication.util.PasswordUtil;
import com.discovery.voyager.aplication.validator.RegistrationDtoAssistant;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class RegistrationController {

    private static Logger log = LogManager.getLogger(RegistrationController.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RegistrationDTO registrationDTO;

    @Autowired
    private OtpFormDto otpFormDto;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ErrorExceptionService errorExceptionService;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @RequestMapping(value ="/profile/register", method = RequestMethod.GET)
    public String displayRegistrationForm(Model model){
        log.debug("entering controller displayRegistrationForm");
        model.addAttribute("registrationBean", registrationDTO);
        log.debug("exiting controller displayRegistrationForm");
        return "profile/register/RegistrationUser";
    }

    @RequestMapping(value = "/profile/registration", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("registrationBean")RegistrationDTO registerData,BindingResult bindingResult, Model model){
        log.debug("entering controller registerUser");
        log.debug("param : "+ registerData);
        log.debug("integrity validation start ");
        //validando dto datos requeridos.
        if (bindingResult.hasErrors()){
            return "profile/register/RegistrationUser";
        }
        log.debug("integrity validation finish ");

        log.debug("pattern validation start ");

        try{
            RegistrationDtoAssistant.validationIntegrityPasswordField(registerData);
        } catch (PasswordInvallidPatternException e) {
            String error = errorExceptionService.findByCode(ConstantAplication.PASSWORD_INVALID_PATTERN_ERROR_CODE).getDescription();
            model.addAttribute("registrationBean", registerData);
            bindingResult.rejectValue("password", "error.registrationBean", error);
            return "profile/register/RegistrationUser";
        }

        //validando passsword
        if(!registerData.getPassword().equals(registerData.getConfirmPassword())){
            String error = errorExceptionService.findByCode(ConstantAplication.INVALID_MATCH_PASSWORD_ERROR_CODE).getDescription();
            bindingResult.rejectValue("password", "error.registrationBean", error);
            model.addAttribute("registrationBean", registerData);
            return "profile/register/RegistrationUser";
        }

        //validando nombre usuario
        User validationUser = userService.findByUsernameAndStatusActive(registerData.getUsername());
        if(validationUser != null){
            String error = errorExceptionService.findByCode(ConstantAplication.DUPLICATE_USERNAME_ERROR_CODE).getDescription();
            bindingResult.rejectValue("username", "error.registrationBean", error);
            model.addAttribute("registrationBean", registerData);
            return "profile/register/RegistrationUser";
        }
        log.debug("pattern validation finish ");

        //enviando email registration 
        String otp = PasswordUtil.generatePassword(6);
        log.debug("sending email process start ");

        boolean sendEmail = true;
        try {
            emailServiceImpl.sentOtpValidationEmail(otp, registerData.getEmail());
        } catch (IOException | TemplateException e) {
            sendEmail=false;
            log.error("error when send email");
            log.error("error : "+  e.getMessage());
        }
        log.debug("sending email process end... ");
        log.debug("creation user to save ");
        Role rol =  roleRepository.findByName(ConstantAplication.ROLE_USER);
        String encodPass = bCryptPasswordEncoder.encode(registerData.getPassword());
        User newUser = RegistrationDtoAssistant.convertToEntity(registerData, rol, encodPass,otp, sendEmail);
        newUser = userService.createUser(newUser);
        log.debug("user created: "+ newUser);
        return "redirect:/profile/register/"+newUser.getUsername()+"/validation/otp";
    }

    @RequestMapping(value ="/profile/register/{username}/validation/otp",  method = RequestMethod.GET)
    public String OtpValidationFormSubmit(@PathVariable("username") String username , Model model){
        log.debug("entering controller displayOtpValidationForm");
        log.debug("param : "+ username);
        User userFound = userService.findByUsername(username);
        if(userFound == null){
            return "redirect:/profile/register";
        }
        otpFormDto.setUsername(username);
        model.addAttribute("otpField", otpFormDto);
        log.debug("exiting controller displayOtpValidationForm");
        return "profile/register/RegistrationOtpValidation";
    }


    @RequestMapping(value ="/profile/register/validation/otp", method = RequestMethod.POST)
    public String displayOtpValidationForm(@Valid @ModelAttribute("otpField") OtpFormDto otpForm, BindingResult bindingResult, Model model){
        log.debug("entering controller displayOtpValidationForm");
        log.debug("param: "+ otpForm);
        if (bindingResult.hasErrors()){
            return "profile/register/RegistrationOtpValidation";
        }
        User userFound = userService.findByUsername(otpForm.getUsername());
        log.debug("validate otp.. ");
        if(otpForm.getOtpTextField().equals(userFound.getOtpEmailConfirmation().getOtpSending())){
            log.debug("otp is valid redirect to login and update user.. ");
            userFound.setStatus(ConstantAplication.STATUS_A);
            userFound.getOtpEmailConfirmation().setOtpValidated(true);
            userService.updateUser(userFound);
            return "redirect:/login";
        }
        String error = errorExceptionService.findByCode(ConstantAplication.INVALID_MATCH_OTP_ERROR_CODE).getDescription();
        bindingResult.rejectValue("otpTextField", "error.otpField", error);
        return "profile/register/RegistrationOtpValidation";
    }

}
