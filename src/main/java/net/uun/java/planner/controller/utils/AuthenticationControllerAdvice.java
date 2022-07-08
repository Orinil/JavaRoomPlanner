package net.uun.java.planner.controller.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthenticationControllerAdvice {


    @ExceptionHandler(NotAuthenticated.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void notAuthenticated() {
    }

    @ExceptionHandler(NotAuthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void notAuthorized() {
    }
}
