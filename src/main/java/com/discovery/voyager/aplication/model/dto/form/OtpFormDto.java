package com.discovery.voyager.aplication.model.dto.form;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@Component
public class OtpFormDto {

    @NotBlank
    private String otpTextField;
    private String username;

    public OtpFormDto(){}

    public OtpFormDto(String otpTextField){
        this.otpTextField = otpTextField;
    }

}
