
package com.discovery.voyager.aplication.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class SMTPConfiguration{

    @Value("${smtp.configuration.email.host}")
    private String host;

    @Value("${smtp.configuration.email.port}")
    private int port;

    @Value("${smtp.configuration.email.username}")
    private String username;

    @Value("${smtp.configuration.email.password}")
    private String password;

    @Bean
    public JavaMailSender getJavaMailSender() {
        System.out.println("host:"+host);
        System.out.println("port:"+port);
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
         
        return mailSender;
    }

}