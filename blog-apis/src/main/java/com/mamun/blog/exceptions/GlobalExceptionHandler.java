package com.mamun.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mamun.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //If client enter any wrong id then Resource not found exception class will take the exception message
    // and then GlobalExceptionHandler class will took that message and it will handle using apiresponse class.
    @ExceptionHandler(ResourceNotFoundException.class)
   ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
         
       String message=ex.getMessage();
       ApiResponse apiResponse=new ApiResponse(message, false);

       return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(MethodArgumentNotValidException.class)
   ResponseEntity<Map<String ,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
    
    //if we send wrong info of user then this method will check all the field of wrong value and it will traverse and give output of 
    //specific wrong value field and will return with message in key value pair
    Map<String ,String> response= new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error)->{
       String field= ((FieldError)error).getField();
       String msg= error.getDefaultMessage();
       response.put(field, msg);

      });
    return new ResponseEntity<Map<String, String>>(response,HttpStatus.BAD_REQUEST) ;
   }

   @ExceptionHandler(ApiException.class)
   ResponseEntity<ApiResponse> usernamePasswordHandler(ApiException ex){
         
       String message=ex.getMessage();
       ApiResponse apiResponse=new ApiResponse(message, false);

       return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
   }
}
