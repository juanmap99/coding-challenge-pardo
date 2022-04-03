package com.codingchallenge.challenge.controller.error_handling;


import com.codingchallenge.challenge.exceptions.TasaOperacionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@ControllerAdvice
public class TasaExceptionHandler {
    @ExceptionHandler(TasaOperacionException.class)
    public ResponseEntity<ErrorOperacion> manejarTasaOperacionException(TasaOperacionException ex) {
        ErrorOperacion errorOp = new ErrorOperacion();
        errorOp.setMessage(ex.getMessage());
        errorOp.setStatus(ex.getStatus());
        errorOp.setTime(new Date().toString());

        return new ResponseEntity<ErrorOperacion>(errorOp, ex.getStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorOperacion> manajarMissingServletRequestParameter(
            MissingServletRequestParameterException ex) {
        String error = "El parametro " + ex.getParameterName() + " no fue otorgado";

        ErrorOperacion errorOp = new ErrorOperacion();
        errorOp.setMessage(error);
        errorOp.setStatus(HttpStatus.BAD_REQUEST);
        errorOp.setTime(new Date().toString());

        return new ResponseEntity<ErrorOperacion>(errorOp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<ErrorOperacion> manejarMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex) {
        String error =
                ex.getName() + " deberia ser de tipo " + ex.getRequiredType().getName();

        ErrorOperacion errorOp = new ErrorOperacion();
        errorOp.setMessage(error);
        errorOp.setStatus(HttpStatus.BAD_REQUEST);
        errorOp.setTime(new Date().toString());

        return new ResponseEntity<ErrorOperacion>(errorOp, HttpStatus.BAD_REQUEST);
    }
}
