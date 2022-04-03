package com.codingchallenge.challenge;

import java.time.LocalDate;

import com.codingchallenge.challenge.exceptions.*;
import com.codingchallenge.challenge.tarjeta.Amex;
import com.codingchallenge.challenge.tarjeta.Nara;
import com.codingchallenge.challenge.tarjeta.Tarjeta;
import com.codingchallenge.challenge.tarjeta.Visa;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_operacion.TasaOperacion;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaAmex;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaNara;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_tarjeta.TasaVisa;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EjercicioUno {
    Amex tarjetaAmex;
    Nara tarjetaNara;
    Visa tarjetaVisa;

    @Before
    public void setup(){
        try {
            this.tarjetaAmex = new Amex("1545256512459874",
                    "Alberto Domínguez",
                    LocalDate.of(2022, 4, 23)
                    );
            this.tarjetaNara = new Nara("1630259122452156",
                    "Carlos Mesa",
                    LocalDate.of(2021, 6, 3)
            );
            this.tarjetaVisa = new Visa("1545256512459874",
                    "Camila Benítez",
                    LocalDate.of(2025, 7, 12)
            );
        }
        catch(NumeroTarjetaInvalidoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void tarjetaValidaOperar_true_fechaPosteriorALaActual(){
        Nara tarjetaNaraDummy = this.tarjetaNara;
        Boolean resultadoActual = null;
        try {
            //Para garantizar que este test funcione independientemente del dia que se lo corra en el futuro
            tarjetaNaraDummy.actualizarFechaVencimiento(LocalDate.now().plusDays(1));
            resultadoActual = tarjetaNaraDummy.puedeOperar();
        }catch(FechaInvalidaException inv){
            resultadoActual = false;
        }
        Assert.assertEquals(resultadoActual,true);
    }

    @Test
    public void tarjetaValidaOperar_false_fechaAnteriorALaActual(){
        Assert.assertEquals(this.tarjetaNara.puedeOperar(),false);
    }

    @Test
    public void sonIguales_false_MarcasDiferentes(){
        Assert.assertEquals(Tarjeta.sonIguales(this.tarjetaNara,this.tarjetaAmex),false);
    }

    @Test
    public void sonIguales_false_NumerosDiferentes(){
        Nara dummy = null;
        try {
            dummy = new Nara("4630259122452156",
                    "Carlos Mesa",
                    LocalDate.of(2021, 6, 3)
            );
        } catch (NumeroTarjetaInvalidoException e) {
            System.out.println(e.getMessage());
        }

        Assert.assertEquals(Tarjeta.sonIguales(this.tarjetaNara,dummy),false);
    }

    @Test
    public void sonIguales_false_TarjetaEsNull(){
        Assert.assertEquals(Tarjeta.sonIguales(this.tarjetaNara,null),false);
    }

    @Test
    public void sonIguales_true_SonMismaTarjeta(){
        Assert.assertEquals(Tarjeta.sonIguales(this.tarjetaNara,this.tarjetaNara),true);
    }

    @Test
    public void operacionValida_true_ImporteEs500(){
        Assert.assertEquals(TasaOperacion.esImporteValido(500),true);
    }

    @Test
    public void operacionValida_false_ImporteEsMenosUno(){
        Assert.assertEquals(TasaOperacion.esImporteValido(-1),false);
    }

    @Test
    public void operacionValida_false_ImporteEsMayorAMil(){
        Assert.assertEquals(TasaOperacion.esImporteValido(1500),false);
    }

    @Test
    public void informacionTarjeta_devuelveLoEsperado(){
        String resultadoEsperado = "{'marca':NARA,'numeroTarjeta':1630259122452156," +
                "'cardholder':Carlos Mesa,'fechaVencimiento':2021-06-03}";
        Assert.assertEquals(resultadoEsperado,this.tarjetaNara.getInformacionTarjeta());
    }

    @Test(expected = ExcesoImporteException.class)
    public void tasaOperacion_excesoImporteException_ImporteMayor1000() throws ExcesoImporteException, MarcaInvalidaException {
        TasaOperacion tasaOp = new TasaOperacion("NARA",1500);
    }

    @Test(expected = MarcaInvalidaException.class)
    public void tasaOperacion_marcaInvalidaException_MarcaNoExiste() throws ExcesoImporteException, MarcaInvalidaException {
        TasaOperacion tasaOp = new TasaOperacion("XANT",400);
    }

    @Test()
    public void tasaServicio_Nara_resultadoValido(){
        double tasa = this.tarjetaNara.getTasaServicio();
        Assert.assertEquals(new TasaNara().getTasaServicio(),tasa,0);
    }

    @Test()
    public void tasaServicio_Visa_resultadoValido(){
        double tasa = this.tarjetaVisa.getTasaServicio();
        Assert.assertEquals(new TasaVisa().getTasaServicio(),tasa,0);
    }

    @Test()
    public void tasaServicio_Amex_resultadoValido(){
        double tasa = this.tarjetaAmex.getTasaServicio();
        Assert.assertEquals(new TasaAmex().getTasaServicio(),tasa,0);
    }

    @Test
    public void tasaOperacion_tarjetaNara_resultadoValido() throws ExcesoImporteException, MarcaInvalidaException, TasaTarjetaNullException {
        int importe = 400;
        TasaOperacion tasaOp = new TasaOperacion("NARA",importe);
        Assert.assertEquals(this.tarjetaNara.getTasaOperacion(importe),tasaOp.calcularTasaOperacion().getTasaOperacion(),0);
    }

    @Test
    public void tasaOperacion_tarjetaVisa_resultadoValido() throws ExcesoImporteException, MarcaInvalidaException, TasaTarjetaNullException {
        int importe = 400;
        TasaOperacion tasaOp = new TasaOperacion("VISA",importe);
        Assert.assertEquals(this.tarjetaVisa.getTasaOperacion(importe),tasaOp.calcularTasaOperacion().getTasaOperacion(),0);
    }

    @Test
    public void tasaOperacion_tarjetaAmex_resultadoValido() throws ExcesoImporteException, MarcaInvalidaException, TasaTarjetaNullException {
        int importe = 400;
        TasaOperacion tasaOp = new TasaOperacion("AMEX",importe);
        Assert.assertEquals(this.tarjetaAmex.getTasaOperacion(importe),tasaOp.calcularTasaOperacion().getTasaOperacion(),0);
    }

    @Test(expected = NumeroTarjetaInvalidoException.class)
    public void creacionTarjeta_lanzaExcepcion_NumeroTarjetaInvalido() throws NumeroTarjetaInvalidoException {
        Tarjeta tarj = new Nara("15c66","Juan Perez",LocalDate.now());
    }

    @Test(expected = NumeroTarjetaInvalidoException.class)
    public void creacionTarjeta_lanzaExcepcion_NumeroTarjetaVacio() throws NumeroTarjetaInvalidoException {
        Tarjeta tarj = new Nara("","Juan Perez",LocalDate.now());
    }
}
