package com.example.websitemanageschooltinhdong.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorResponse {
    public ErrorResponse(String message, boolean status, Map<String, String> details) {
        super();
        this.message = message;
        this.errors = details;
        this.satus = status;
    }

    //General error message about nature of error
    private String message;
    private boolean satus;
    //Specific errors in API request processing
    private Map<String, String> errors;

    //Getter and setters
}