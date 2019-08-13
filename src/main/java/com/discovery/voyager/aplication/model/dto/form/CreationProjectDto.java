package com.discovery.voyager.aplication.model.dto.form;

import lombok.Data;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Component
public class CreationProjectDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;

    List<String> username;

    public CreationProjectDto(){}


}
