package com.discovery.voyager.aplication.handler;

import com.discovery.voyager.aplication.exception.InternalApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class InternalApplicationErrorHandler {

    /**
     * Handler exception that returns an http-500 internal server error when an error was not handled properly.
     */
    @ExceptionHandler({InternalApplicationException.class, NullPointerException.class})
    public String handleAplicationError(Throwable throwable, HttpServletRequest request, HttpServletResponse response)  {
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String mesage = "error{\"error\":  \"in aplication\"}";
        return "error/GeneralErrorPage";
    }






}
