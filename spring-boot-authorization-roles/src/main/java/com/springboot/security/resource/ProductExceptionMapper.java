package com.springboot.security.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;

@RestControllerAdvice
public class ProductExceptionMapper   {
    private static final Logger log = LoggerFactory.getLogger(ProductExceptionMapper.class);


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDetails getRuntimeException(RuntimeException ex){
        log.error("Exception message "+ ex);
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode("5000");
        errorDetails.setErrorMessage(ex.getMessage());
        return errorDetails;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    ErrorDetails getCustomExceptionOne(RuntimeException ex){
        log.error("Exception message "+ ex);

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode("5001");
        errorDetails.setErrorMessage(ex.getMessage());
        return errorDetails;

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    ErrorDetails getAnother(InternalServerErrorException ex){
        log.error("Exception message "+ ex);

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode("5001");
        errorDetails.setErrorMessage(ex.getMessage());
        return errorDetails;

    }



}
