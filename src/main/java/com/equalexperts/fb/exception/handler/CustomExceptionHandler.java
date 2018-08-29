package com.equalexperts.fb.exception.handler;

import com.equalexperts.fb.exception.IllegalParameterException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.lang.String.format;

@ControllerAdvice
@Component
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MISSING_REQUEST_PARAMETER_MESSAGE = "'%s' is required, please provide";
    public static final String METHOD_PARAMETER_TYPE_MISMATCH = "Value [%s] is not accepted for parameter '%s'";
    public static final String GENERIC_TYPE_MISMATCH = "Value [%s] is not accepted";

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status,
                                                                          WebRequest request) {

        return new ResponseEntity<>(format(MISSING_REQUEST_PARAMETER_MESSAGE, ex.getParameterName()), status);
    }

    @ExceptionHandler(IllegalParameterException.class)
    public ResponseEntity<Object> handleIllegalParameterException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = format(GENERIC_TYPE_MISMATCH, ex.getValue());
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException methodArgumentEx = (MethodArgumentTypeMismatchException) ex;
            message = format(METHOD_PARAMETER_TYPE_MISMATCH, ex.getValue(), methodArgumentEx.getName());
        }
        return new ResponseEntity<>(message, status);
    }
}
