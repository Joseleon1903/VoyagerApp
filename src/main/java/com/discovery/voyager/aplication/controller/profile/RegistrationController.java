package com.discovery.voyager.aplication.controller.profile;

import com.discovery.voyager.aplication.constant.ConstantAplication;
import com.discovery.voyager.aplication.model.dto.EmailDTO;
import com.discovery.voyager.aplication.model.dto.GenericErrorDTO;
import com.discovery.voyager.aplication.model.dto.RegistrationDTO;
import com.discovery.voyager.aplication.model.entity.EmailTemplate;
import com.discovery.voyager.aplication.model.entity.Profile;
import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.service.implementation.EmailServiceImpl;
import com.discovery.voyager.aplication.service.implementation.EmailTemplateService;
import com.discovery.voyager.aplication.service.implementation.ErrorExceptionService;
import com.discovery.voyager.aplication.service.implementation.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

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
        model.addAttribute("errorBean", genericErrorDTO);
        model.addAttribute("registrationBean", registrationDTO);
        return "profile/register/RegistrationUser";
    }

    @RequestMapping(value = "/profile/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute(value="registerData") RegistrationDTO registerData, Model model){
        User newUser =  new User();
        Profile newProfile = new Profile();

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

        //enviando email registration 
        EmailTemplate template = emailTemplateService.findByCode(ConstantAplication.REGISTRATION_EMAIL_CODE);

        EmailDTO email = new EmailDTO();
        email.setHeader("Welcome "+registerData.getUsername());
        email.setDestinationEmail(registerData.getProfile().getEmail());
        email.setContent(template.getContent());

        emailServiceImpl.sendEmailTo(email);

        newProfile.setFirstName(registerData.getProfile().getFirstName());
        newProfile.setLastName(registerData.getProfile().getLastName());
        newProfile.setMobilePhone(registerData.getProfile().getMobilePhone());
        newProfile.setEmail(registerData.getProfile().getEmail());
        newUser.setPassword(registerData.getPassword());
        newUser.setUsername(registerData.getUsername());
        newUser.setProfile(newProfile);
        newUser =userService.createUser(newUser);
        System.out.println("usuario registrdo: "+ newUser);
        return "profile/register/RegistrationSuccess";
    }

}
