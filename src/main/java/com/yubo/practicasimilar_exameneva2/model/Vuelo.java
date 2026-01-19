package com.yubo.practicasimilar_exameneva2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "vuelos")
public class Vuelo {

    @Id
    @Column(name = "idvuelo")
    private String idvuelo;


    private String horasalida;
    private String origen;
    private String destino;
    private float precio;
    private int numeroescalas;


    @ManyToOne
    @JoinColumn(name = "idcompañia", referencedColumnName="idcompañia")
    @JsonBackReference
    @ToString.Exclude
    private Compania compania;

}
