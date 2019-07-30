package com.discovery.voyager.aplication.model.dto;

import lombok.Data;

@Data
public class EmailDTO{


    private String destinationEmail;
    private String header;
    private String content;

    public EmailDTO(){}


}