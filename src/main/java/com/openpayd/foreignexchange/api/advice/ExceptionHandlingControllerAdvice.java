package com.openpayd.foreignexchange.api.advice;

import com.openpayd.foreignexchange.api.dto.ValidationErrorResponse;
import com.openpayd.foreignexchange.api.dto.Violation;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.UUID;


@Slf4j
@ControllerAdvice
public class ExceptionHandlingControllerAdvice {
    private static final String ERROR_CODE_PREFIX = "Error code is {}";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onRequestBodyMissingException(HttpMessageNotReadableException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        Violation violation = new Violation("", e.getLocalizedMessage());
        error.setViolations(Collections.singletonList(violation));
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handle(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        UUID randomUUID = UUID.randomUUID();
        log.error(ERROR_CODE_PREFIX, randomUUID.toString(), ex);
        return "Please check logs. Error code is " + randomUUID.toString();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public String handleAccessDeniedExceptions(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        UUID randomUUID = UUID.randomUUID();
        log.error(ERROR_CODE_PREFIX, randomUUID.toString(), ex);
        return "Please check logs. Error code is " + randomUUID.toString();
    }

    @ExceptionHandler(FeignException.class)
    @ResponseBody
    public String handleFeignException(FeignException ex, HttpServletRequest request) {
        UUID randomUUID = UUID.randomUUID();
        String feignExceptionDescription = "Error happened during call to RatesProvider.";
        log.error(feignExceptionDescription + ERROR_CODE_PREFIX, randomUUID.toString(), ex);
        return feignExceptionDescription + "Please check logs. Error code is " + randomUUID.toString();
    }

}
