package com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta;

import java.time.LocalDate;

public class TasaVisa extends TasaTarjeta {
    @Override
    public double getTasaServicio() {
        LocalDate curDate = LocalDate.now();
        /*
            Segun el ejemplo del enunciado sobre el cálculo de la tajera visa (20/12)
            concluyo que no hay que usar el año en su totalidad sino que debo tomar
            en cuenta solo los dos últimos números para realizar el cálculo.
            Debido a eso, aplicando módulo de 100 podemos conseguir facilmente dicho valor.
         */
        int year = curDate.getYear()%100;
        //Debido a que el mes no puede ser cero no hay que corroborar errores
        double tasa = year/curDate.getMonth().getValue();
        return this.ajustarTasaABoundaries(tasa);
    }
}
