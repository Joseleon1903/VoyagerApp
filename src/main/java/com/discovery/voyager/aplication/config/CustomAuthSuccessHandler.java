package com.discovery.voyager.aplication.config;

import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static Logger log = LogManager.getLogger(CustomAuthSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.debug("entering in Autentication success");
        UserDetails principal =(UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        User user = userService.findByUsername(username);
        user.setLastLoginDate(new Date());
        userService.updateUser(user);
        redirectStrategy.sendRedirect(request, response, "/home");
    }


}
