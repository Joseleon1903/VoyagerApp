package com.discovery.voyager.aplication.controller;

import javax.servlet.http.HttpServletResponse;
import com.discovery.voyager.aplication.constant.ConstantAplication;
import com.discovery.voyager.aplication.model.entity.ErrorException;
import com.discovery.voyager.aplication.service.implementation.ErrorExceptionService;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private ErrorExceptionService errorExceptionService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){ 
        model.addAttribute("errorBean", "");   
        return "login";
    }

    @RequestMapping(value = "/loginError", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model){
        ErrorException errorE = errorExceptionService.findByCode(ConstantAplication.INVALID_USER_ERROR_CODE);
        System.out.println("error en la autenticacion status: "+ errorE.getDescription());
        model.addAttribute("errorBean", errorE.getDescription());
        return "login";
    }
}
