package com.discovery.voyager.aplication.controller.profile;

import com.discovery.voyager.aplication.constant.ConstantAplication;
import com.discovery.voyager.aplication.exception.RequiredFieldException;
import com.discovery.voyager.aplication.model.dto.EmailDTO;
import com.discovery.voyager.aplication.model.dto.GenericErrorDTO;
import com.discovery.voyager.aplication.model.dto.form.RegistrationDTO;
import com.discovery.voyager.aplication.model.entity.EmailTemplate;
import com.discovery.voyager.aplication.model.entity.Profile;
import com.discovery.voyager.aplication.model.entity.Role;
import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.repository.RoleRepository;
import com.discovery.voyager.aplication.service.implementation.EmailServiceImpl;
import com.discovery.voyager.aplication.service.implementation.EmailTemplateService;
import com.discovery.voyager.aplication.service.implementation.ErrorExceptionService;
import com.discovery.voyager.aplication.service.implementation.UserServiceImpl;
import com.discovery.voyager.aplication.validator.RegistrationDtoAssistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
    private GenericErrorDTO genericErrorDTO;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ErrorExceptionService errorExceptionService;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @RequestMapping(value ="/profile/register", method = RequestMethod.GET)
    public String displayRegistrationForm(Model model){
        log.debug("entering controller displayRegistrationForm");
        model.addAttribute("errorBean", genericErrorDTO);
        model.addAttribute("registrationBean", registrationDTO);
        log.debug("exiting controller displayRegistrationForm");
        return "profile/register/RegistrationUser";
    }

    @RequestMapping(value = "/profile/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute(value="registerData") RegistrationDTO registerData, Model model){

        //validando dto datos requeridos.
        try{
            RegistrationDtoAssistence.validationRequiredField(registerData);
        }catch(RequiredFieldException ex){
            String error = errorExceptionService.findByCode(ConstantAplication.REQUIRED_FIELD_ERROR_CODE).getDescription();
            genericErrorDTO.setErrorDetail(error);
            model.addAttribute("errorBean",genericErrorDTO);
            return "redirect:/profile/register";
        }

        //validando passsword
        if(!registerData.getPassword().equals(registerData.getConfirmPassword())){
            String error = errorExceptionService.findByCode(ConstantAplication.INVALID_MATCH_PASSWORD_ERROR_CODE).getDescription();
            genericErrorDTO.setErrorDetail(error);
            model.addAttribute("errorBean",genericErrorDTO);
            return "redirect:/profile/register";
        }

        //validando nombre usuario
        User validationUser = userService.findByUsername(registerData.getUsername());
        if(validationUser != null){
            String error = errorExceptionService.findByCode(ConstantAplication.DUPLICATE_USERNAME_ERROR_CODE).getDescription();
            genericErrorDTO.setErrorDetail(error);
            model.addAttribute("errorBean",genericErrorDTO);
            return "redirect:profile/register";
        }

        Role rol =  roleRepository.findByName(ConstantAplication.ROLE_USER);
        String encodPass = bCryptPasswordEncoder.encode(registerData.getPassword());
        User newUser = RegistrationDtoAssistence.convertToEntity(registerData, rol, encodPass);
        newUser =userService.createUser(newUser);

        //enviando email registration
        EmailTemplate template = emailTemplateService.findByCode(ConstantAplication.REGISTRATION_EMAIL_CODE);

        EmailDTO email = new EmailDTO();
        email.setHeader("Welcome "+registerData.getUsername());
        email.setDestinationEmail(registerData.getProfile().getEmail());
        email.setContent(template.getContent());
        emailServiceImpl.sendEmailTo(email);
        System.out.println("usuario registrdo: "+ newUser);
        return "profile/register/RegistrationSuccess";
    }

}
