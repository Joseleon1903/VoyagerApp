package com.discovery.voyager.aplication.service.controller;

import com.discovery.voyager.aplication.model.dto.form.CreationProjectDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/test")
public class ControllertestRest {

    private static Logger log = LogManager.getLogger(ControllertestRest.class);

    @RequestMapping(value ="/management/page/dashboard/creation/project", method = RequestMethod.POST)
    public String formCreationProject(@Valid @RequestBody CreationProjectDto projectDto, BindingResult bindingResult, Model model){
        log.debug("entering controller formCreationProject");
        log.debug("entering controller registerUser");
        if (bindingResult.hasErrors()){
            return "error";
        }

       return "success";
    }

}
