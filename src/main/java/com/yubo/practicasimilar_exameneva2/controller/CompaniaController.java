package com.yubo.practicasimilar_exameneva2.controller;

import com.yubo.practicasimilar_exameneva2.model.Compania;
import com.yubo.practicasimilar_exameneva2.service.CompaniaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/compania")
public class CompaniaController {


    private final CompaniaServices companiaServices;

    public CompaniaController(CompaniaServices companiaServices) {
        this.companiaServices = companiaServices;
    }

    // c.     Registrar un nuevo hotel
    @PostMapping("save")
    /*
    POST   http://localhost:9999/api/hotel/save
    {
    "nombre": "Hotel Valencia Center",
    "descripcion": "Un hotel moderno cerca del arte",
    "categoria": 4,
    "piscina": true,
    "localidad": "Valencia"
    }


    */
    public ResponseEntity<?> createHotel(@RequestBody Compania compania) {
        try {
            // se vuelve datos que has creado
            Compania companiaGuardado = companiaServices.saveCompania(compania);
            //  CREADO (si se guardó correctamente)
            return new ResponseEntity<>(companiaGuardado, HttpStatus.CREATED);
        } catch (Exception e) {
            // SI HAY UN ERROR AL GUARDAR EL HOTEL, SE DEVUELVE UN MENSAJE DE ERROR
            return new ResponseEntity<>("Error al guardar el hotel: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
