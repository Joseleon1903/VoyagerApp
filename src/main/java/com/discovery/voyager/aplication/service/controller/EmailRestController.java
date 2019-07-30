package com.discovery.voyager.aplication.service.controller;

import com.discovery.voyager.aplication.model.dto.EmailDTO;
import com.discovery.voyager.aplication.model.dto.EmailSendDTO;
import com.discovery.voyager.aplication.model.entity.EmailTemplate;
import com.discovery.voyager.aplication.service.implementation.EmailServiceImpl;
import com.discovery.voyager.aplication.service.implementation.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailRestController {

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @RequestMapping(value="/send", method = RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> sendEmail(@RequestBody EmailSendDTO emailSend ){
        EmailTemplate template = emailTemplateService.findByCode(emailSend.getCode());
        EmailDTO emailDto = new EmailDTO();
        emailDto.setDestinationEmail(emailSend.getDestinationEmail());
        emailDto.setHeader(template.getHeader());
        emailDto.setContent(template.getContent());
        emailServiceImpl.sendEmailTo(emailDto);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}