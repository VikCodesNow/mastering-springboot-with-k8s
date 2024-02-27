package com.master.springboot.series.accounts.exceptions;


import com.master.springboot.series.accounts.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorDTO res =  ErrorDTO.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(exception.getMessage())
                .errorTime(LocalDateTime.now()).build();
        return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> validationErrors = new HashMap<>();
        List<ObjectError> allErrors =  ex.getBindingResult().getAllErrors();
        allErrors.forEach(error->{
           String field = ((FieldError)error).getField();
           String msg = error.getDefaultMessage();
           validationErrors.put(field,msg);
        });

        return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
    }
}
