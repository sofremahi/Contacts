package com.tiddev.sample.service.common.exception;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoResourceException.class)
    public ResponseEntity<Object> NoResource(NoResourceException ex , WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                ex.getLocalizedMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails , HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String , String> errors = new HashMap<>();
        List<ObjectError> errorDetailsList = ex.getBindingResult().getAllErrors();
        errorDetailsList.forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName , message);
        });
        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
    }
}
