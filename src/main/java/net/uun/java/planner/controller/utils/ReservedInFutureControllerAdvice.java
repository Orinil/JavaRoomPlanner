package net.uun.java.planner.controller.utils;

import net.uun.java.planner.utils.ReservedInFuture;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReservedInFutureControllerAdvice {


    @ExceptionHandler(ReservedInFuture.class)
    public ResponseEntity<String> reservedInFuture() {
        return new ResponseEntity("reserved in future", HttpStatus.EXPECTATION_FAILED);
    }
}
