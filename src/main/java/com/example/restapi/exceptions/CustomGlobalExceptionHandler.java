package com.example.restapi.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Set;

// without this annotation, the below code is useless
//@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // MethodArgumentNotValidException
    // this was the method that was present in the ResponseEntityExceptionHandler class
    // and we want to make use of MethodArgumentNotValid Exception, therefore we are making use of this function
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // whatever error we want to provide when MethodArgumentNotValidException is thrown
        // so for that we are making use of CustomErrorDetails class
        // this class we have defined already
        // it takes 3 parameters :- Date , String and String
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date() ,
                "Error we are throwing when MethodArgumentNotValid Exception is coming" ,
                ex.getMessage());

        return new ResponseEntity<>(customErrorDetails , HttpStatus.BAD_REQUEST);
    }

    // this is for the Http Request methods that we have not defined
    // like till now we haven't defined any PATCH request, but if any PATCH request is thrown at the server
    // then this error message should be displayed

    //HttpRequestMethodNotSupported Exception
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date() ,
                "Error we are throwing when Method Not allowed is there" ,
                ex.getMessage());

        return new ResponseEntity<>(customErrorDetails , HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex , WebRequest request){
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date() ,
                "Error we are throwing when UserName is not found" ,
                ex.getMessage());

        return new ResponseEntity<>(customErrorDetails , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex , WebRequest request){
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date() ,
                "Error we are throwing when Constraint are not satisfied" ,
                request.getDescription(false));

        return new ResponseEntity<>(customErrorDetails , HttpStatus.BAD_REQUEST);
    }

}
