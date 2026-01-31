package com.flowforge.common;

import com.flowforge.common.exception.InvalidStateTransitionException;
import com.flowforge.common.exception.ResourceNotFoundException;
import com.flowforge.common.exception.UnauthorizedActionException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        return new ErrorResponse(Instant.now(), 404, "Not Found", ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(UnauthorizedActionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleUnauthorized(UnauthorizedActionException ex, HttpServletRequest req) {
        return new ErrorResponse(Instant.now(), 403, "Forbidden", ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        return new ErrorResponse(
                Instant.now(),
                400,
                "Validation Failed",
                ex.getBindingResult().getFieldError().getDefaultMessage(),
                req.getRequestURI()
        );
    }
}
