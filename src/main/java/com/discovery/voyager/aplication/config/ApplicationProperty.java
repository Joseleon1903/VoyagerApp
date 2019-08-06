package com.discovery.voyager.aplication.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ApplicationProperty {

    @Value("${smtp.configuration.email.username}")
    private String fromEmail;


}
