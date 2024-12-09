package com.HealthBizz.Survey.exceptionHandler;

import com.HealthBizz.Survey.dto.ApiResponseDto;
import com.HealthBizz.Survey.exception.UnauthorisedException;
import com.HealthBizz.Survey.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ApiResponseDto exceptionResponseDto = new ApiResponseDto();

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponseDto> handleClientNotFoundException(UserNotFoundException exp) {
        exceptionResponseDto.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponseDto.setMessage(exp.getMessage());
        exceptionResponseDto.setTimeStamp(Instant.now());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponseDto> handleNullPointerException(NullPointerException exp) {
        exceptionResponseDto.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponseDto.setMessage("Null Pointer Encountered");
        exceptionResponseDto.setTimeStamp(Instant.now());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorisedException.class)
    public ResponseEntity<ApiResponseDto> handleAstrologerNotFoundException(UnauthorisedException exp) {
        exceptionResponseDto.setStatus(HttpStatus.UNAUTHORIZED.value());
        exceptionResponseDto.setMessage(exp.getMessage());
        exceptionResponseDto.setTimeStamp(Instant.now());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.UNAUTHORIZED);
    }

    /*@ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ApiResponseDto> handleNotAllowedException(DuplicateDataException exp){
        exceptionResponseDto.setStatus(HttpStatus.FORBIDDEN.value());
        exceptionResponseDto.setMessage(exp.getMessage());
        exceptionResponseDto.setTimeStamp(Instant.now());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.FORBIDDEN);
    }*/

    /*@ExceptionHandler(UserException.class)
    public ResponseEntity<NewAccessTokenResponseDto> handleUserNotFound(UserException exp) {
        exceptionResponseDto.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponseDto.setMessage(exp.getMessage());
        exceptionResponseDto.setTimestamp(String.valueOf(System.currentTimeMillis()));

        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }*/

    /*@ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<NewAccessTokenResponseDto> handleNotAuthenticatedException(NotAuthenticatedException exp){
        exceptionResponseDto.setStatus(HttpStatus.UNAUTHORIZED.value());
        exceptionResponseDto.setMessage(exp.getMessage());
        exceptionResponseDto.setTimestamp(String.valueOf(System.currentTimeMillis()));

        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.UNAUTHORIZED);
    }*/

    @ExceptionHandler
    public ResponseEntity<ApiResponseDto> handleAllExceptions(Exception exp) {
        exceptionResponseDto.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponseDto.setMessage(exp.getMessage());
        exceptionResponseDto.setTimeStamp(Instant.now());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
