package com.discovery.voyager.aplication.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * joseleon
 * 
 */
@Controller
public class HomePageController {

    @RequestMapping({ "/home", "/"})
    public String loginPage(Model model){
        return "dashboard/HomePage";
    }

}
