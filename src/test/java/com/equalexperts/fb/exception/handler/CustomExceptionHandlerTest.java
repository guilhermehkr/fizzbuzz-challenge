package com.equalexperts.fb.exception.handler;

import com.equalexperts.fb.exception.IllegalParameterException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.equalexperts.fb.controller.FizzBuzzController.SEQUENCE_START_PARAMETER_NAME;
import static com.equalexperts.fb.exception.handler.CustomExceptionHandler.*;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomExceptionHandler.class)
public class CustomExceptionHandlerTest {

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    @Test
    public void shouldHandleMissingParameterException() {

        MissingServletRequestParameterException exception = new MissingServletRequestParameterException(SEQUENCE_START_PARAMETER_NAME, "Integer");

        ResponseEntity<Object> responseEntity =
                customExceptionHandler.handleMissingServletRequestParameter(exception, null, BAD_REQUEST, null);

        assertThat(responseEntity.getStatusCode(), is(BAD_REQUEST));
        assertThat(responseEntity.getBody(), is(format(MISSING_REQUEST_PARAMETER_MESSAGE, SEQUENCE_START_PARAMETER_NAME)));
    }

    @Test
    public void shouldHandleIllegalParameterException() {

        String exceptionMessage = "The input parameter is wrong";
        IllegalParameterException illegalParameterException = new IllegalParameterException(exceptionMessage);

        ResponseEntity<Object> responseEntity = customExceptionHandler.handleIllegalParameterException(illegalParameterException);

        assertThat(responseEntity.getStatusCode(), is(BAD_REQUEST));
        assertThat(responseEntity.getBody(), is(exceptionMessage));
    }

    @Test
    public void shouldHandleAnyException() {

        String exceptionMessage = "An internal error happened";
        RuntimeException exception = new RuntimeException(exceptionMessage);

        ResponseEntity<Object> responseEntity = customExceptionHandler.handleAnyException(exception);

        assertThat(responseEntity.getStatusCode(), is(INTERNAL_SERVER_ERROR));
        assertThat(responseEntity.getBody(), is(exceptionMessage));
    }

    @Test
    public void shouldHandleMethodArgumentTypeMismatchException() {

        String parameterValue = "$$$";
        TypeMismatchException exception = new MethodArgumentTypeMismatchException(parameterValue, null, SEQUENCE_START_PARAMETER_NAME, null, null);

        ResponseEntity<Object> responseEntity =
                customExceptionHandler.handleTypeMismatch(exception, null, BAD_REQUEST, null);

        assertThat(responseEntity.getStatusCode(), is(BAD_REQUEST));
        assertThat(responseEntity.getBody(), is(format(METHOD_PARAMETER_TYPE_MISMATCH, parameterValue, SEQUENCE_START_PARAMETER_NAME)));
    }

    @Test
    public void shouldHandleAnyOtherTypeMismatchException() {

        String parameterValue = "$$$";
        TypeMismatchException exception = new MethodArgumentConversionNotSupportedException(parameterValue, null, SEQUENCE_START_PARAMETER_NAME, null, null);

        ResponseEntity<Object> responseEntity =
                customExceptionHandler.handleTypeMismatch(exception, null, BAD_REQUEST, null);

        assertThat(responseEntity.getStatusCode(), is(BAD_REQUEST));
        assertThat(responseEntity.getBody(), is(format(GENERIC_TYPE_MISMATCH, parameterValue)));
    }
}