package com.codingchallenge.challenge.tarjeta;

import com.codingchallenge.challenge.exceptions.NumeroTarjetaInvalidoException;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaAmex;

import java.time.LocalDate;

public class Amex extends Tarjeta{
    public Amex(String numeroTarjeta, String cardholder, LocalDate fechaVencimiento) throws NumeroTarjetaInvalidoException {
        super("AMEX", numeroTarjeta, cardholder, fechaVencimiento, new TasaAmex());
    }

}
