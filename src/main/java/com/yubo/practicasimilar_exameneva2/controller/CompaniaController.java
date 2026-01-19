package com.yubo.practicasimilar_exameneva2.controller;

import com.yubo.practicasimilar_exameneva2.dto.CompaniaNombreDTO;
import com.yubo.practicasimilar_exameneva2.model.Compania;
import com.yubo.practicasimilar_exameneva2.service.CompaniaServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compania")
public class CompaniaController {


    private final CompaniaServices companiaServices;

    public CompaniaController(CompaniaServices companiaServices) {
        this.companiaServices = companiaServices;
    }


    @Operation(
            summary = "Registrar compañía",
            description = "Inserta una nueva compañía. REQUIERE un token JWT válido.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponse(responseCode = "201", description = "Insertardo correctamente")
    @ApiResponse(responseCode = "403", description = "Acceso denegado - Token inválido o inexistente")
    @PostMapping("/save")
    /*
    POST   http://localhost:9000/api/compania/save

    {
        "nombrecompania": "China Southern1"
    }

    */
    public ResponseEntity<?> createCompania(@RequestBody CompaniaNombreDTO dto) {
        try {
            // se vuelve datos que has creado
            Compania compania = new Compania();
            compania.setNombrecompania(dto.getNombrecompania());
            //  CREADO (si se guardó correctamente)
            return new ResponseEntity<>(companiaServices.saveCompania(compania), HttpStatus.CREATED);
        } catch (Exception e) {
            // SI HAY UN ERROR AL GUARDAR EL HOTEL, SE DEVUELVE UN MENSAJE DE ERROR
            return new ResponseEntity<>("Error al guardar la compañía: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Operation(summary = "Dar de baja compañía", security = { @SecurityRequirement(name = "bearerAuth") })
    @DeleteMapping("/delete/{id}")
    // http://localhost:9000/api/compania/delete/13
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            companiaServices.deleteById(id);
            return ResponseEntity.ok("Compañía y sus vuelos eliminados correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



    @Operation(summary = "Modificar nombre compañía", security = { @SecurityRequirement(name = "bearerAuth") })
    @PutMapping("/update/{id}")
    // http://localhost:9000/api/compania/update/10

    // En raw
    // {
    //  "nombrecompania": "China Eastern"
    // }

    public ResponseEntity<?> update(@PathVariable int id, @RequestBody CompaniaNombreDTO dto) {
        try {

            Compania actualizada = companiaServices.updateNombre(id, dto.getNombrecompania());
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
