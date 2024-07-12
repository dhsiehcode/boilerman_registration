package com.boilerman.signupV3;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryNotFoundAdvice {

    @ExceptionHandler(EntryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String collegiateEntryNotFundHandler(EntryNotFoundException ex) {
        return ex.getMessage();
    }
}
