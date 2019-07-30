package com.discovery.voyager.aplication.service.implementation;

import com.discovery.voyager.aplication.model.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

   @Autowired
   private JavaMailSender emailSender;
    
    public boolean sendEmailTo(EmailDTO email) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(email.getDestinationEmail()); 
        message.setSubject(email.getHeader()); 
        message.setText(email.getContent());
        emailSender.send(message);
        return true;
	}

}