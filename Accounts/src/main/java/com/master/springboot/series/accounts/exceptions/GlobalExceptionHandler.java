package com.master.springboot.series.accounts.exceptions;


import com.master.springboot.series.accounts.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(CustomerAlreadyExists.class)
     public ResponseEntity<ErrorDTO> handleCustomerExists(CustomerAlreadyExists exception, WebRequest webRequest) {
       ErrorDTO res =  ErrorDTO.builder()
                 .apiPath(webRequest.getDescription(false))
                 .errorCode(HttpStatus.BAD_REQUEST)
                 .errorMessage(exception.getMessage())
                 .errorTime(LocalDateTime.now()).build();
          return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(CustomerNotFound.class)
     public ResponseEntity<ErrorDTO> handleCustomerNotFound(CustomerNotFound exception, WebRequest webRequest) {
         ErrorDTO res =  ErrorDTO.builder()
                 .apiPath(webRequest.getDescription(false))
                 .errorCode(HttpStatus.NOT_FOUND)
                 .errorMessage(exception.getMessage())
                 .errorTime(LocalDateTime.now()).build();
         return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
     }
}
