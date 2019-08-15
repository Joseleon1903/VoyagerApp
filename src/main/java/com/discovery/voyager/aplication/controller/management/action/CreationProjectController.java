package com.discovery.voyager.aplication.controller.management.action;

import com.discovery.voyager.aplication.model.dto.form.CreationProjectDto;
import com.discovery.voyager.aplication.model.dto.form.UsernameChecked;
import com.discovery.voyager.aplication.service.implementation.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class CreationProjectController {

    private static Logger log = LogManager.getLogger(CreationProjectController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CreationProjectDto creationProjectDto;

    private String name;

    @RequestMapping(value = "/management/page/dashboard/creation/project/{projectName}", method = RequestMethod.GET)
    public String managementDashboardCreationProject(@PathVariable(value = "projectName") String projectName, Model model){
        log.debug("entering method managementDashboardCreationProject..");
        log.debug("Project name"+ projectName);
        this.name= projectName;
        model.addAttribute("projectName",projectName);
        model.addAttribute("projectDto",creationProjectDto);


        return "dashboard/management/action/CreateProjectMgPage";
    }


    @RequestMapping(value ="management/page/dashboard/creation/project", method = RequestMethod.POST)
    public String formCreationProject(@Valid @ModelAttribute("projectDto")CreationProjectDto projectDto, BindingResult bindingResult, Model model){
        log.debug("entering controller formCreationProject");
        log.debug("entering controller registerUser");

        if(projectDto.getSearchtext() != null && !projectDto.getSearchtext().isEmpty()){
            userService.findListUsername(projectDto.getSearchtext()).forEach( it ->{
                UsernameChecked check = new UsernameChecked(it, false);
                projectDto.getUsernameList().add(check);
            });
            projectDto.setSearchtext("");
            this.creationProjectDto = projectDto;
            return "redirect:/management/page/dashboard/creation/project/"+name;
        }
        log.debug("integrity validation start ");
        //validando dto datos requeridos.
        if (bindingResult.hasErrors()){
            this.creationProjectDto = projectDto;
            return "redirect:/management/page/dashboard/creation/project/"+name;
        }
        log.debug("integrity validation finish ");

        name = "";
        return "redirect:/management/page/dashboard";
    }

}
