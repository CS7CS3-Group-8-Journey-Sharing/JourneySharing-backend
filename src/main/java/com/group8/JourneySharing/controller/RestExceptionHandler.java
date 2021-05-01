package com.group8.JourneySharing.controller;

import com.group8.JourneySharing.entity.ErrorResponse;
import com.group8.JourneySharing.exception.BadRequestException;
import com.group8.JourneySharing.exception.DuplicateRecordException;
import com.group8.JourneySharing.exception.JourneySharingException;
import com.group8.JourneySharing.exception.NoRecordsFetchedException;
import org.apache.commons.lang.exception.ExceptionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    final static Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {


        ErrorResponse body = new ErrorResponse();
        body.setErrorCode(status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> {
                    LOGGER.error("Validation error: " + x.getDefaultMessage());
                    return x.getDefaultMessage();})
                .collect(Collectors.toList());

        body.setErrorMessage(errors.toString());
        return new ResponseEntity<>(body, headers, status);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error("HttpMessageNotReadableException:------------ " + ex);
        LOGGER.error("Exception: " + ExceptionUtils.getStackTrace(ex));
        HttpHeaders header = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorMessage(Arrays.asList(ex.getMessage()).toString());
        return new ResponseEntity<>(response, header, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        LOGGER.error("BadRequestException:------------ " + ex);
        LOGGER.error("Exception: " + ExceptionUtils.getStackTrace(ex));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorMessage(Arrays.asList(ex.getMessage()).toString());
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        LOGGER.error("RuntimeException Exception:------------ " + ex);
        LOGGER.error("Exception: " + ExceptionUtils.getStackTrace(ex));
        ErrorResponse response = new ErrorResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setErrorMessage(Arrays.asList(ex.getMessage()).toString());
        return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorResponse> handleJsonProcessingException(JsonProcessingException ex) {
        LOGGER.error("JsonProcessing Exception:------------ " + ex);
        LOGGER.error("Exception: " + ExceptionUtils.getStackTrace(ex));
        ErrorResponse response = new ErrorResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setErrorMessage(Arrays.asList(ex.getMessage()).toString());
        return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateRecordException(DuplicateRecordException ex) {
        LOGGER.error("DuplicateRecordException:------------ " + ex);
        LOGGER.error("Exception: " + ExceptionUtils.getStackTrace(ex));
        ErrorResponse response = new ErrorResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setErrorMessage(Arrays.asList(ex.getMessage()).toString());
        return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoRecordsFetchedException.class)
    public ResponseEntity<ErrorResponse> handleNoRecordsFetchedException (NoRecordsFetchedException ex) {
        LOGGER.error("NoRecordsFetchedException:------------ " + ex);
        LOGGER.error("Exception: " + ExceptionUtils.getStackTrace(ex));
        ErrorResponse response = new ErrorResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setErrorMessage(Arrays.asList(ex.getMessage()).toString());
        return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JourneySharingException.class)
    public ResponseEntity<ErrorResponse> handleJourneySharingException(Exception ex) {
        LOGGER.error("JourneySharingException:------------ " + ex);
        LOGGER.error("Exception: " + ExceptionUtils.getStackTrace(ex));
        ErrorResponse response = new ErrorResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setErrorMessage(Arrays.asList(ex.getMessage()).toString());
        return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}