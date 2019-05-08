package com.group40.hjemmesalgrestws.exceptions;

import com.group40.hjemmesalgrestws.io.models.exceptions.response.ErrorMessageRest;
import com.group40.hjemmesalgrestws.io.models.user.reponse.UserRest;
import com.group40.hjemmesalgrestws.service.CategoryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class BackendExceptionsHandler {

    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object>handleUserServiceException(UserServiceException e, WebRequest request){

        ErrorMessageRest errorMessageRest = new ErrorMessageRest(new Date(), 666, e.getMessage(), e.getFix() );

        return new ResponseEntity<>(errorMessageRest, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {CategoryServiceException.class})
    public ResponseEntity<Object>handleCategoryServiceException(CategoryServiceException e, WebRequest request){
        ErrorMessageRest errorMessageRest = new ErrorMessageRest(new Date(), 666, e.getMessage(), e.getFix() );

        return new ResponseEntity<>(errorMessageRest, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {AdServiceException.class})
    public ResponseEntity<Object>handleAdServiceException(AdServiceException e, WebRequest request){
        ErrorMessageRest errorMessageRest = new ErrorMessageRest(new Date(), 666, e.getMessage(), e.getFix() );

        return new ResponseEntity<>(errorMessageRest, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
