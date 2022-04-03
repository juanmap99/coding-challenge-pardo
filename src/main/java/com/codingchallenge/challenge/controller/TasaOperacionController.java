package com.codingchallenge.challenge.controller;

import com.codingchallenge.challenge.controller.request_entities.TasaTarjetaRequest;
import com.codingchallenge.challenge.exceptions.ExcesoImporteException;
import com.codingchallenge.challenge.exceptions.MarcaInvalidaException;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_operacion.TasaOperacion;
import com.codingchallenge.challenge.tarjeta.tasas.tasas_operacion.TasaOperacionRespuesta;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value="/")
public class TasaOperacionController {
    @RequestMapping(
            value = "/tasatarjeta",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<TasaOperacionRespuesta> getTasa(@RequestBody TasaTarjetaRequest tasaReq) throws ExcesoImporteException, MarcaInvalidaException, MissingServletRequestParameterException {
        if(tasaReq == null || tasaReq.getMarca() == null){
            throw new MissingServletRequestParameterException("marca","String");
        }
        if(tasaReq.getImporte() == null){
            throw new MissingServletRequestParameterException("importe","Double");
        }
        TasaOperacion tasaOp = new TasaOperacion(tasaReq.getMarca(),tasaReq.getImporte());
        TasaOperacionRespuesta tasa = tasaOp.calcularTasaOperacion();
        return new ResponseEntity<TasaOperacionRespuesta>(tasa,HttpStatus.OK);
    }

}
