package com.portfolio.argprograma.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter @Setter
@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proyecto_id")
    private int id;

    private String nombre;
    private String url;
    private String descripcion;

}
