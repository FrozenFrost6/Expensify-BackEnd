package com.anvesh.expensify.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UnauthorizedAccessException extends RuntimeException{
	
	
	public UnauthorizedAccessException(String message) {
        super(message);
    }
    
}
