package com.portfolio.argprograma.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String url;
    private String descripcion;
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Tecnologia> listaDeTecnologias;
}
