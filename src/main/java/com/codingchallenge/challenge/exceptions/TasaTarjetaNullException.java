package com.codingchallenge.challenge.exceptions;

public class TasaTarjetaNullException extends Exception{
    public TasaTarjetaNullException(){
        super("Error: La tasa de la tarjeta no puede ser NULL.");
    }
}
