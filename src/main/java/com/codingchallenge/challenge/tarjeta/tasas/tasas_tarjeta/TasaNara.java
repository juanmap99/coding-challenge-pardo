package com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta;

import java.time.LocalDate;

public class TasaNara extends TasaTarjeta {

    @Override
    public double getTasaServicio() {
        LocalDate curDate = LocalDate.now();
        double tasa = curDate.getDayOfMonth()*0.5;
        return this.ajustarTasaABoundaries(tasa);
    }
}
