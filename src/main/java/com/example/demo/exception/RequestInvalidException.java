package com.example.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by VuHien96 on 27/05/2020 - 2:19 PM
 */
@ResponseStatus
public class RequestInvalidException extends RuntimeException{
    public RequestInvalidException (String message){
        super(message);
    }
}
