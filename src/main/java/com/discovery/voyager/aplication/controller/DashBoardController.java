package  com.discovery.voyager.aplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashBoardController{

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String login(Model model){ 
        return "dashboard/HomePage";
    }

}