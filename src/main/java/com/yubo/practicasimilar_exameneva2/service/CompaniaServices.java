package com.yubo.practicasimilar_exameneva2.service;

import com.yubo.practicasimilar_exameneva2.model.Compania;
import com.yubo.practicasimilar_exameneva2.repository.CompaniaRepository;
import org.springframework.stereotype.Service;

@Service
public class CompaniaServices {
    private final CompaniaRepository companiaRepository;

    public CompaniaServices(CompaniaRepository companiaRepository) {
        this.companiaRepository = companiaRepository;
    } // CONSTRUCTOR PARA AÑADIR EL REPOSITORIO COMPAÑIA EN EL SERVICIO


    public Compania saveCompania(Compania compania) { return companiaRepository.save(compania); }


    public void deleteById(int id) {
        if (companiaRepository.existsById(id)) {
            companiaRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encuentra la compañía con ID: " + id);
        }
    }


    public Compania updateNombre(int id, String nuevoNombre) {
        Compania c = companiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compañía no encontrada"));
        c.setNombrecompania(nuevoNombre);
        return companiaRepository.save(c);
    }

}
