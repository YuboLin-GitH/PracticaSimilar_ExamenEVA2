package com.yubo.practicasimilar_exameneva2.controller;


import com.yubo.practicasimilar_exameneva2.model.Vuelo;

import com.yubo.practicasimilar_exameneva2.service.VueloServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/vuelo")
public class VueloController {

    private final VueloServices vueloServices;

    public VueloController(VueloServices vueloServices) {
        this.vueloServices = vueloServices;
    }

    @GetMapping("/")
    // http://localhost:9000/api/vuelo/
    public List<Vuelo> getAllHabitacion() {
        try {
            return vueloServices.findAllVuelos();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener todos los vuelos", e);
        }

    }

    // http://localhost:9000/api/vuelo/buscarvuelos?origen=MADRID&destino=LIMA&numeroescalas=1
    @GetMapping("/buscarvuelos")
    public ResponseEntity<?> getVueloPorODE(@RequestParam String origen, @RequestParam String destino, @RequestParam int numeroescalas){
        try {
            // SE LLAMA AL SERVICIO PARA OBTENER TODOS LOS VUELOS SEGÚN LOS FILTROS
            List<Vuelo> vuelos = vueloServices.buscarVuelos(origen, destino, numeroescalas);
            System.out.printf(vuelos.toString());
            if (vuelos.isEmpty()) {
                return new ResponseEntity<>("No existe ningun vuelo", HttpStatus.NOT_FOUND);
            } // SI NO HAY VUELOS, DEVUELVE UN MENSAJE

            return new ResponseEntity<>(vuelos.toString(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener vuelo por filtro origen , destino y escalas", e);
        }
    }

    // http://localhost:9000/api/vuelo/buscardestinoprecio?destino=MADRID&precioMax=1000.0
    @GetMapping("/buscardestinoprecio")
    public ResponseEntity<?> getVueloPorDestinoPrecio(@RequestParam String destino, @RequestParam float precioMax) {
        try {
            List<Vuelo> vuelos = vueloServices.buscarVuelosPorDestinoyPrecio(destino, precioMax);
            if (vuelos.isEmpty()) {
                return new ResponseEntity<>("No hay vuelos a " + destino + " por menos de " + precioMax, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(vuelos, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al filtrar por precio", e);
        }
    }



    // http://localhost:9000/api/vuelo/numeroVuelosOrigen?origen=MADRID
    @GetMapping("/numeroVuelosOrigen")
    public ResponseEntity<?> getNumeroVuelosOrigen(@RequestParam String origen) {
        try {
            int vuelos = vueloServices.numeroVuelosOrigen(origen);
            if (vuelos == 0) {
                return new ResponseEntity<>("No existe ningun vuelo con ese origen " , HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(vuelos, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al listar todos los vuelos del origen", e);
        }
    }


}
