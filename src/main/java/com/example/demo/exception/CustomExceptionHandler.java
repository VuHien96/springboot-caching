package com.example.demo.exception;

import com.example.demo.locale.Translator;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by VuHien96 on 26/05/2020 - 10:35 AM
 */

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), details);
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(RequestInvalidException.class)
    public final ResponseEntity<Object> handleRequestInvalidException(RequestInvalidException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), Translator.toLocale("error.msg.request.invalid"), details);
        return ResponseEntity.ok(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), Translator.toLocale("error.msg.request.invalid"), details);
        return ResponseEntity.ok(error);
    }


//    @Override
//    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
//        HttpStatus status = clientHttpResponse.getStatusCode();
//        return status.is4xxClientError() || status.is5xxServerError();
//    }
//
//    @SneakyThrows
//    @Override
//    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
//        String responseAsString = toString(clientHttpResponse.getBody());
//        // log.error("ResponseBody: {}", responseAsString);
//        throw new CustomException(responseAsString);
//    }
//
//    @SneakyThrows
//    @Override
//    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
//        String responseAsString = toString(response.getBody());
//        //log.error("URL: {}, HttpMethod: {}, ResponseBody: {}", url, method, responseAsString);
//        throw new CustomException(responseAsString);
//    }

//    String toString(InputStream inputStream) {
//        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
//        return s.hasNext() ? s.next() : "";
//    }

}
