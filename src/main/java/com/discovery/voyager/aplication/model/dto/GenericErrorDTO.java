package com.discovery.voyager.aplication.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by Jose Eduardo on 6/22/2018.
 */
@Data
@Component
public class GenericErrorDTO implements Serializable{

    private static final long serialVersionUID = -1093132142235886293L;
    
    private String errorDetail = "";
    private List<String> fieldError = new ArrayList<>();

    public GenericErrorDTO() {
    }

    public GenericErrorDTO(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public GenericErrorDTO(String errorDetail ,  List<String> fieldError) {
        this.errorDetail = errorDetail;
        this.fieldError = fieldError;
    }

    public void clear(){
        this.errorDetail = "";
        this.fieldError = new ArrayList<>();
    }

    public boolean hasError(String errorName){
        return fieldError.contains(errorName);
    }

}
