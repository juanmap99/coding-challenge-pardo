package com.codingchallenge.challenge.controller.request_entities;

public class TasaTarjetaRequest {
    private String marca;
    private Double importe;

    public TasaTarjetaRequest(String marca, Double importe) {
        this.marca = marca;
        this.importe = importe;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }
}
