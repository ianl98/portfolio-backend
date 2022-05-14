package com.portfolio.argprograma.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    @OneToOne
    @JoinColumn(name = "nivel_id")
    private Nivel nivel;
}
