package com.codingchallenge.challenge.tarjeta;

import com.codingchallenge.challenge.exceptions.NumeroTarjetaInvalidoException;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaNara;

import java.time.LocalDate;

public class Nara extends Tarjeta{
    public Nara(String numeroTarjeta, String cardholder, LocalDate fechaVencimiento) throws NumeroTarjetaInvalidoException {
        super("NARA", numeroTarjeta, cardholder, fechaVencimiento, new TasaNara());
    }

}
