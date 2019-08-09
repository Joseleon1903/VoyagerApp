package com.discovery.voyager.aplication.controller.management;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardPageMgController {

    private static Logger log = LogManager.getLogger(DashboardPageMgController.class);

    @RequestMapping(value = "/management/page/dashboard", method = RequestMethod.GET)
    public String managementDashboard(Model model){
        log.debug("entering method managementDashboard..");
        return "dashboard/management/DashboardMgPage";
    }

}
