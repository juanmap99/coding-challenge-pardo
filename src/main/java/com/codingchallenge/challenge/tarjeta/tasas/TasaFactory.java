package com.codingchallenge.challenge.tarjeta.tasas;

import com.codingchallenge.challenge.exceptions.MarcaInvalidaException;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaAmex;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaNara;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaTarjeta;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaVisa;

public class TasaFactory {
    /**
     * Método que crea una TasaTarjeta en base a un string que determina
     * la marca de tarjeta deseada.
     *
     * @param marcaTarjeta String que representa la marca de una tarjeta
     * @return TasaTarjeta propia a la tarjeta requerida
     * @throws MarcaInvalidaException
     */
    public static TasaTarjeta getTasaTarjeta(String marcaTarjeta) throws MarcaInvalidaException {
        if(marcaTarjeta.equalsIgnoreCase("VISA")){
            return new TasaVisa();
        }
        if(marcaTarjeta.equalsIgnoreCase("NARA")){
            return new TasaNara();
        }
        if(marcaTarjeta.equalsIgnoreCase("AMEX")){
            return new TasaAmex();
        }
        throw new MarcaInvalidaException();
    }
}
