package com.portfolio.argprograma.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonBackReference
    @JoinTable(name = "proyectos_tecnologias",
            joinColumns = @JoinColumn(name = "proyecto_id", referencedColumnName = "proyecto_id"),
    inverseJoinColumns = @JoinColumn(name = "tecnologia_id", referencedColumnName = "tecnologia_id"))
    private Set<Tecnologia> tecnologias = new HashSet<>();
}
