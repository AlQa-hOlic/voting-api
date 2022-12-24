package in.alqaholic.VotingAPI.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.alqaholic.VotingAPI.dto.VotingApiResponse;
import in.alqaholic.VotingAPI.exceptions.ApiException;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<VotingApiResponse> apiExceptionHandler(ApiException e) {
        return ResponseEntity.status(e.getStatus()).body(new VotingApiResponse(e.getMessage()));
    }
}
