package com.discovery.voyager.aplication.model.dto;

import lombok.Data;

@Data
public class SystemError {

    private long code;
    private String decription;
    private long httpStatus;
}
