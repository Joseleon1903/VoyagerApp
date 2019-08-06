package com.discovery.voyager.aplication.controller;

import com.discovery.voyager.aplication.exception.InternalApplicationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * joseleon
 * 
 */
@Controller
public class HomePageController {

    private static Logger log = LogManager.getLogger(HomePageController.class);

    @RequestMapping({ "/home", "/"})
    public String homePage(Model model){
        log.debug("entering in method homePage");
        return "dashboard/HomePage";
    }

}
