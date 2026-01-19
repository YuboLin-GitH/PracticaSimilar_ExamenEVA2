package com.yubo.practicasimilar_exameneva2.service;

import com.yubo.practicasimilar_exameneva2.repository.CompaniaRepository;
import org.springframework.stereotype.Service;

@Service
public class CompaniaServices {
    private final CompaniaRepository companiaRepository;

    public CompaniaServices(CompaniaRepository companiaRepository) {
        this.companiaRepository = companiaRepository;
    } // CONSTRUCTOR PARA AÑADIR EL REPOSITORIO COMPAÑIA EN EL SERVICIO

}
