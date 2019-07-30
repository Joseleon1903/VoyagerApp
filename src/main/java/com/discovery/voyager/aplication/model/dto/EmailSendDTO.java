package com.discovery.voyager.aplication.model.dto;

import lombok.Data;

@Data
public class EmailSendDTO{

    private String code;
    private String destinationEmail;

    public EmailSendDTO(){}

    public EmailSendDTO(String code, String destinationEmail){
        this.code = code;
        this.destinationEmail = destinationEmail;
    }


}