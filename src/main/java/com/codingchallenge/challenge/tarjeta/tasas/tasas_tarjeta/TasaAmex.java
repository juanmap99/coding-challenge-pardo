package com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta;

import java.time.LocalDate;

public class TasaAmex extends TasaTarjeta {
    @Override
    public double getTasaServicio() {
        LocalDate curDate = LocalDate.now();
        double tasa = curDate.getMonth().getValue()*0.1;
        return this.ajustarTasaABoundaries(tasa);
    }
}
