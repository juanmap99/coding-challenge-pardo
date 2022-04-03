package com.codingchallenge.challenge.exceptions;


public class MarcaInvalidaException extends TasaOperacionException{

    public MarcaInvalidaException(){
        super("Error: La marca indicada debe encontrarse dentro de las marcas comprendidas en el sistema.");
    }
}
