package com.yubo.practicasimilar_exameneva2.service;

import com.yubo.practicasimilar_exameneva2.model.Vuelo;
import com.yubo.practicasimilar_exameneva2.repository.VueloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloServices {
    private final VueloRepository vueloRepository;

    public VueloServices(VueloRepository vueloRepository) { this.vueloRepository = vueloRepository; }

    public List<Vuelo> findAllVuelos(){
        return vueloRepository.findAll();
    }


    // a.	Búsqueda de vuelos, pudiendo filtrar por estos 3 campos origen, destino y numero de escalas.

    public List<Vuelo> buscarVuelos(String origen, String destino, int numeroescalas) {
        return vueloRepository.findVueloByOrigenDestinoNumeroescalas(origen, destino, numeroescalas);
    }

    // b.	Búsqueda de vuelos, pudiendo filtrar por destino y que su precio no supere cierta cantidad.

    public List<Vuelo> buscarVuelosPorDestinoyPrecio(String destino, float numeroescalas) {
        return vueloRepository.findVueloByDestinoPrecio(destino, numeroescalas);
    }

    // c.	Numero de vuelos que salen desde un origen y que no tengan escalas

    public int numeroVuelosOrigen(String origen) {
        return vueloRepository.findVueloByOrigen(origen);
    }


}
