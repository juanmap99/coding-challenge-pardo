package com.codingchallenge.challenge.tarjeta.tasas.tasas_operacion;

//Esta clase puede extenderse según cambios de requirimientos
public class TasaOperacionRespuesta {
    private double tasaOperacion;

    public TasaOperacionRespuesta(double tasaOperacion){
        this.tasaOperacion = tasaOperacion;
    }

    public double getTasaOperacion() {
        return tasaOperacion;
    }
}
