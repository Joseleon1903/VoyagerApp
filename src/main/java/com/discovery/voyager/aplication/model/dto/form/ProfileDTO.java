package com.discovery.voyager.aplication.model.dto.form;

import lombok.Data;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Component
public class ProfileDTO implements Serializable {

    private static final long serialVersionUID = -6459950174350417512L;


    public ProfileDTO(){}

}