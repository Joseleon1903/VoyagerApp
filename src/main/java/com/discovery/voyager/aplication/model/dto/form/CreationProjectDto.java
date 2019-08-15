package com.discovery.voyager.aplication.model.dto.form;

import lombok.Data;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class CreationProjectDto {

    private String projectName;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String searchtext;

    private List<UsernameChecked> usernameList =  new ArrayList<>();

    public CreationProjectDto(){}



}
