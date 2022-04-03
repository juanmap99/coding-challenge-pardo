package com.codingchallenge.challenge.tarjeta.tasas.tasas_operacion;

import com.codingchallenge.challenge.exceptions.ExcesoImporteException;
import com.codingchallenge.challenge.exceptions.MarcaInvalidaException;
import com.codingchallenge.challenge.exceptions.TasaTarjetaNullException;
import com.codingchallenge.challenge.tarjeta.tasas.TasaFactory;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaTarjeta;

public class TasaOperacion {
    private TasaTarjeta tasa;
    private double importe;

    /**
     * Instancia una TasaOperacion siempre y cuando la marcaTarjeta enviada
     * y el importe detallado sean válidos.
     *
     * @param marcaTarjeta String que representa a la marca sobre la cual basarse para calcular
     *                    los intereses
     * @param importe Entero que determina el importe de la operación.
     * @throws ExcesoImporteException
     * @throws MarcaInvalidaException
     */
    public TasaOperacion(String marcaTarjeta, double importe) throws ExcesoImporteException, MarcaInvalidaException{
        if(!TasaOperacion.esImporteValido(importe)){
            throw new ExcesoImporteException();
        }
        this.tasa = TasaFactory.getTasaTarjeta(marcaTarjeta);
        this.importe = importe;
    }

    public TasaOperacion(TasaTarjeta tasa, double importe) throws ExcesoImporteException, TasaTarjetaNullException {
        if(!TasaOperacion.esImporteValido(this.importe)){
            throw new ExcesoImporteException();
        }
        if(tasa == null){
            throw new TasaTarjetaNullException();
        }
        this.tasa = tasa;
        this.importe = importe;
    }

    /**
     * Determina si una operación es válida o no. El enunciado especificaba que
     * un importe mayor a 1000 se consideraba como inválido pero no especificaba si
     * ese monto era el inicial o el resultante posterior al aplicamiento de los
     * intereses de la tarjeta. Debido a la ambiguedad presentada, decidí de manera
     * arbitraria que se refería al monto base previo al cálculo de intereses.
     *
     * @param importe Valor que representa el importe de la operación
     * @return True si la operación es válida, False en caso contrario.
     */
    public static boolean esImporteValido(double importe){
        if(importe >= 1000 || importe < 0){
            return false;
        }
        return true;
    }

    /**
     * Calcula la tasa de una operación.
     *
     * @return Valor que representa la tasa de una operacion porcentual.
     */
    public TasaOperacionRespuesta calcularTasaOperacion(){
        double tasa = (this.tasa.getTasaServicio()*this.importe)/100;
        return new TasaOperacionRespuesta(tasa);
    }
}
