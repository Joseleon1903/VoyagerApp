package com.discovery.voyager.aplication.service.controller;

import com.discovery.voyager.aplication.controller.profile.RegistrationController;
import com.discovery.voyager.aplication.model.dto.EmailDTO;
import com.discovery.voyager.aplication.model.dto.EmailSendDTO;
import com.discovery.voyager.aplication.model.entity.EmailTemplate;
import com.discovery.voyager.aplication.service.implementation.EmailServiceImpl;
import com.discovery.voyager.aplication.service.implementation.EmailTemplateService;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/email")
public class EmailRestController {

    private static Logger log = LogManager.getLogger(RegistrationController.class);

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @PostMapping(value="/send")
    @ResponseBody
    public ResponseEntity sendEmail(@RequestBody EmailSendDTO emailSend ){
        log.debug("Entering in method sendEmail");
        log.debug("param: "+ emailSend);
        log.debug("search Email template");
        EmailTemplate template = emailTemplateService.findByCode(emailSend.getCode());
        log.debug("template: "+ template);
        try {
            emailServiceImpl.sentOtpValidationEmail("12345", emailSend.getDestinationEmail());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}