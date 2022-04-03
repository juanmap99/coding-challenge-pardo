package com.codingchallenge.challenge.exceptions;


public class ExcesoImporteException extends TasaOperacionException{
    public ExcesoImporteException(){
        super("Operacion invalida: El costo de la operación debe ser menor a $1000 y mayor a $0.");
    }

}
