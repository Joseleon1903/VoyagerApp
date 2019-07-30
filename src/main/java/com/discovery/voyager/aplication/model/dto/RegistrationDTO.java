package com.discovery.voyager.aplication.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class RegistrationDTO implements Serializable {

    private static final long serialVersionUID = 8297240807703876682L;
    
	private String username;
    private String password;
    private String confirmPassword;
    private ProfileDTO profile;

    public RegistrationDTO(){}

}
