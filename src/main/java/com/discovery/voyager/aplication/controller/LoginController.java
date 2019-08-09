package com.discovery.voyager.aplication.controller;

import javax.servlet.http.HttpServletResponse;
import com.discovery.voyager.aplication.constant.ConstantApplication;
import com.discovery.voyager.aplication.model.entity.ErrorException;
import com.discovery.voyager.aplication.service.implementation.ErrorExceptionService;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * joseleon1903
 */
@Controller
public class LoginController {

    private static Logger log = LogManager.getLogger(HomePageController.class);

    @Autowired
    private ErrorExceptionService errorExceptionService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){ 
        model.addAttribute("errorBean", "");   
        return "login";
    }

    @RequestMapping(value = "/loginError", method = RequestMethod.POST)
    public String loginError(HttpServletRequest request, HttpServletResponse response, Model model){
        ErrorException errorE = errorExceptionService.findByCode(ConstantApplication.INVALID_USER_ERROR_CODE);
        log.error("error en la autenticacion status: "+ errorE.getDescription());
        model.addAttribute("errorBean", errorE.getDescription());
        return "login";
    }
}
