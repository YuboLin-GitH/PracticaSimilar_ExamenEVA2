package com.yubo.practicasimilar_exameneva2.repository;

import com.yubo.practicasimilar_exameneva2.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VueloRepository  extends JpaRepository<Vuelo, String> {


    // a.	Búsqueda de vuelos, pudiendo filtrar por estos 3 campos origen, destino y numero de escalas.

    @Query("SELECT v FROM Vuelo v WHERE v.origen = :origen AND v.destino = :destino AND v.numeroescalas = :numeroescalas")
    List<Vuelo> findVueloByOrigenDestinoNumeroescalas(@Param("origen") String origen,
                                                      @Param("destino") String destino,
                                                      @Param("numeroescalas") int numeroescalas);


    // b.	Búsqueda de vuelos, pudiendo filtrar por destino y que su precio no supere cierta cantidad.
    @Query("SELECT v FROM Vuelo v WHERE v.destino = :destino AND v.precio <= :precioMax")
    List<Vuelo> findVueloByDestinoPrecio(@Param("destino") String destino,
                                         @Param("precioMax") float precioMax);


    // c.	Numero de vuelos que salen desde un origen y que no tengan escalas
    @Query("select COUNT(v) from Vuelo v where v.origen = :origen AND v.numeroescalas = 0")
    int countVuelosByOrigenSinEscalas(@Param("origen") String origen);
}
