package com.codingchallenge.challenge.tarjeta;

import com.codingchallenge.challenge.exceptions.NumeroTarjetaInvalidoException;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaVisa;

import java.time.LocalDate;

public class Visa extends Tarjeta{
    public Visa(String numeroTarjeta, String cardholder, LocalDate fechaVencimiento) throws NumeroTarjetaInvalidoException {
        super("VISA", numeroTarjeta, cardholder, fechaVencimiento, new TasaVisa());
    }

}
