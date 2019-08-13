package com.discovery.voyager.aplication.controller.management.action;

import com.discovery.voyager.aplication.constant.ConstantApplication;
import com.discovery.voyager.aplication.model.dto.form.CreationProjectDto;
import com.discovery.voyager.aplication.model.dto.form.OtpFormDto;
import com.discovery.voyager.aplication.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CreationProjectController {

    private static Logger log = LogManager.getLogger(CreationProjectController.class);

    @Autowired
    private CreationProjectDto creationProjectDto;

    @RequestMapping(value = "/management/page/dashboard/creation/project/{projectName}", method = RequestMethod.GET)
    public String managementDashboardCreationProject(@PathVariable(value = "projectName") String projectName, Model model){
        log.debug("entering method managementDashboardCreationProject..");
        log.debug("Project name"+ projectName);
        model.addAttribute("projectName",projectName);
        model.addAttribute("projectDto",creationProjectDto);


        return "dashboard/management/action/CreateProjectMgPage";
    }


    @RequestMapping(value ="management/page/dashboard/creation/project/users", method = RequestMethod.GET)
    public String formCreationProject(BindingResult bindingResult, Model model){
        log.debug("entering controller formCreationProject");
        log.debug("entering controller registerUser");
        log.debug("integrity validation start ");
        //validando dto datos requeridos.
        if (bindingResult.hasErrors()){
            return "dashboard/management/action/CreateProjectMgPage";
        }
        log.debug("integrity validation finish ");

        return "redirect:/management/page/dashboard";
    }

}
