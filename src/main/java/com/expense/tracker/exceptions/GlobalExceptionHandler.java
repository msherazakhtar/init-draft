package com.expense.tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.enums.ResponseStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponse<String>> handleUserExists(UserAlreadyExistException exception) {
        System.out.println("Exception Occurred : " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ApiResponse<>(exception.getMessage(), null, ResponseStatus.NOT_ALLOWED));
    }

    @ExceptionHandler(MailConfigurationNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleMailConfiguration(MailConfigurationNotFoundException exception) {
        System.out.println("Exception Occurred : " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ApiResponse<>(exception.getMessage(), null, ResponseStatus.NOT_ALLOWED));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> hanldeUserNotFound(UserNotFoundException exception) {
        System.out.println("Exception Occurred : " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ApiResponse<>(exception.getMessage(), null, ResponseStatus.NOT_ALLOWED));
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handExpenseNotFound(ExpenseNotFoundException exception) {
        System.out.println("Exception Occurred : " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ApiResponse<>(exception.getMessage(), null, ResponseStatus.NOT_ALLOWED));
    }
    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidOtpException(InvalidOtpException exception) {
        System.out.println("Exception Occurred : " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ApiResponse<>(exception.getMessage(), null, ResponseStatus.NOT_ALLOWED));
    }
}
