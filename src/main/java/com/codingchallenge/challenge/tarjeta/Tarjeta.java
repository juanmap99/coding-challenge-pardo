package com.codingchallenge.challenge.tarjeta;

import com.codingchallenge.challenge.exceptions.*;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_operacion.TasaOperacion;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaTarjeta;

import java.time.LocalDate;

public abstract class Tarjeta {
    private String marca;
    /*
        Se optó por representar el número de la tarjeta en formato String no solamente
        para optimizar memoria sino que a su vez no podemos determinar con certeza que en un
        futuro no existirá una tarjeta con una longitud que escape el rango de valores comprendidos
        en un long.
    */
    private String numeroTarjeta;
    private String cardholder;
    private LocalDate fechaVencimiento;
    private TasaTarjeta tasa;


    public Tarjeta(String marca, String numeroTarjeta, String cardholder, LocalDate fechaVencimiento, TasaTarjeta tasa) throws NumeroTarjetaInvalidoException {
        if(!this.esNumeroTarjetaValido(numeroTarjeta)){
            throw new NumeroTarjetaInvalidoException("El número de la tarjeta solo puede estar compuesto por valores numéricos.");
        }
        this.marca = marca;
        this.numeroTarjeta = numeroTarjeta;
        this.cardholder = cardholder;
        this.fechaVencimiento = fechaVencimiento;
        this.tasa = tasa;
    }

    /**
     * Determina la validez de un número de tarjeta. Debido a que el enunciado no
     * especificaba el rango de longitudes para considerar una tarjeta válida o inválida,
     * el único criterio utilizado es corroborar que todos los valores sean números enteros
     * válidos.
     *
     * @param numeroTarjeta String que representa el numero de la tarjeta
     * @return True si es un número válido, False en caso contrario.
     */
    private boolean esNumeroTarjetaValido(String numeroTarjeta){
        if(numeroTarjeta.length() == 0)return false;
        for(int i=0; i<numeroTarjeta.length();i++){
            if(!Character.isDigit(numeroTarjeta.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula la tasa de servicio de la tarjeta haciendo uso del polimorfismo.
     *
     * @return Tasa de servicio de la tarjeta de manera porcentual.
     */
    public double getTasaServicio(){
        return this.tasa.getTasaServicio();
    }

    /**
     * Calcula la tasa de operación de la tarjeta dado un importe.
     *
     * @param importe Valor que representa el importe
     * @return Tasa de la operacion considerando el importe y los intereses
     *         de la tarjeta.
     * @throws ExcesoImporteException
     * @throws TasaTarjetaNullException
     */
    public double getTasaOperacion(double importe) throws ExcesoImporteException, TasaTarjetaNullException {
        TasaOperacion tasaOp = new TasaOperacion(this.tasa,importe);
        return tasaOp.calcularTasaOperacion().getTasaOperacion();
    }

    /**
     * Determina si dos tarjetas son iguales o no. Debido a que el enunciado no lo
     * especificada, el criterio optado para determinar si dos tarjetas son iguales
     * es si ambas son de la misma marca y poseen el mismo número de tarjeta.
     *
     * @param tarjetaA parametro que representa a la tarjetaA
     * @param tarjetaB parametro que representa a la tarjetaB
     * @return True en caso que las tarjetas sean iguales, False en caso contrario.
     */
    public static boolean sonIguales(Tarjeta tarjetaA, Tarjeta tarjetaB){
        if(tarjetaA == null || tarjetaB == null){
            return false;
        }
        if(!tarjetaA.marca.equals(tarjetaB.marca)){
            return false;
        }
        return tarjetaA.numeroTarjeta.equals(tarjetaB.numeroTarjeta);
    }

    /**
     * Determina si una tarjeta puede operar o no.
     * @return True en caso que la tarjeta pueda operar, False en caso contrario.
     */
    public boolean puedeOperar(){
        return this.fechaVencimiento.isAfter(LocalDate.now());
    }

    /**
     * Retorna la información de la tarjeta en formato json.
     *
     * @return Información de la tarjeta en formato json
     */
    public String getInformacionTarjeta(){
        /*
            Se utilizó StringBuilder debido a que la concatenación de dos string se realiza
            bajo una complejidad temporal O(N+M) en donde N es la longitud del primer string y M
            es la longitud del segundo, lo que ocurrira sobre cada concatenación realentizando el cálculo.
            Sin embargo,con el StringBuilder podemos realizar un append() en un orden temporal O(1) y en el único
            momento en donde debemos recorrer la cadena de manera lineal es solamente al final
            cuando corremos toString();
         */
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("'marca':");
        sb.append(this.marca);
        sb.append(",");
        sb.append("'numeroTarjeta':");
        sb.append(this.numeroTarjeta);
        sb.append(",");
        sb.append("'cardholder':");
        sb.append(this.cardholder);
        sb.append(",");
        sb.append("'fechaVencimiento':");
        //El formato estandar de LocalTime es yyyy-mm-dd
        sb.append(this.fechaVencimiento.toString());
        sb.append("}");

        return sb.toString();
    }

    /**
     * Actualiza la fecha de vencimiento de la tarjeta en base a la fecha recibida
     * por parametro. En caso de que la misma sea inválida, se lanza FechaInvalidaException.
     *
     * @param nuevaFecha fecha que determina la nueva fecha de vencimiento.
     * @throws FechaInvalidaException
     */
    public void actualizarFechaVencimiento(LocalDate nuevaFecha) throws FechaInvalidaException {
        if(nuevaFecha != null && nuevaFecha.isBefore(LocalDate.now())){
            throw new FechaInvalidaException("Error: La nueva fecha de vencimiento no puede ser anterior al día de la fecha.");
        }
        this.fechaVencimiento = nuevaFecha;
    }
}
