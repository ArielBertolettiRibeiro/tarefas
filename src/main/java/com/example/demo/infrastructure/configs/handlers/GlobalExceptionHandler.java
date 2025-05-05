package com.example.demo.infrastructure.configs.handlers;

import com.example.demo.application.exceptions.DuplicateUserException;
import com.example.demo.application.exceptions.TaskNotFoundException;
import com.example.demo.application.exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorReponse> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorReponse> handleTaskNotFound(TaskNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ErrorReponse> handleDuplicateUser(DuplicateUserException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorReponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(this::formatFieldError)
                .toList();

        ErrorReponse errorReponse = new ErrorReponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "Invalid input fields.",
                request.getRequestURI(),
                errors
        );

        return ResponseEntity.badRequest().body(errorReponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorReponse> buildErrorResponse(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ErrorReponse> buildErrorResponse(Exception ex, HttpStatus status, HttpServletRequest request) {
        ErrorReponse errorReponse = new ErrorReponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(status).body(errorReponse);
    }

    private String formatFieldError(FieldError fieldError) {
        return String.format("Field '%s': %s", fieldError.getField(), fieldError.getDefaultMessage());
    }

}
