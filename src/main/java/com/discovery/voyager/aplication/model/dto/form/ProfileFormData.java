package com.discovery.voyager.aplication.model.dto.form;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class ProfileFormData {
  
    public String firstName;
    public String username;
    public String lastName;
    public String email;
    public String mobilePhone;
    public String profileImageUrl;

    public ProfileFormData(){}

}