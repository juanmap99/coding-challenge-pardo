package com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta;

public abstract class TasaTarjeta {
    //El enunciado establece limites a las tasas disponibles.
    private final double TASA_MAXIMA=5.0;
    private final double TASA_MINIMA=0.3;

    /**
     * Calcula la tasa de servicio porcentual de una tarjeta.
     *
     * @return Tasa de servicio porcentual de una tarjeta.
     */
    public abstract double getTasaServicio();

    /**
     * Ajusta la tasa según los boundaries establecidos en el enunciado.
     *
     * @param tasa Valor que representa la tasa de una tarjeta
     * @return Tasa ajustada segun los boundaries establecidos.
     */
    protected double ajustarTasaABoundaries(double tasa){
        if(tasa > this.TASA_MAXIMA){
            return this.TASA_MAXIMA;
        }
        if(tasa < this.TASA_MINIMA){
            return this.TASA_MAXIMA;
        }
        return tasa;
    }

}
