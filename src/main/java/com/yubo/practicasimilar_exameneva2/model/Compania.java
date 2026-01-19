package com.yubo.practicasimilar_exameneva2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "Compañias")
public class Compania {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompañia")
    private int idcompania;

    @Column(name = "nombrecompañia")
    private String nombrecompania;

    @OneToMany(mappedBy = "compania", cascade = CascadeType.ALL)
    @JsonManagedReference //OJO ahi que ponerlo para que no sea un bucle infinito
    // ERROR --> Infinite Recursion with Jackson JSON and Hibernate JPA
    @ToString.Exclude
    private List<Vuelo> vuelos ;


}
