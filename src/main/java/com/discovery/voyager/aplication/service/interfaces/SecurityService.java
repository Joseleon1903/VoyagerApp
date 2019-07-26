package com.discovery.voyager.aplication.service.interfaces;

public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);

}