package com.portfolio.argprograma.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tecnologia_id")
    private int id;

    private String nombre;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "proyectos_tecnologias",
            joinColumns = @JoinColumn(name = "tecnologia_id", referencedColumnName = "tecnologia_id"),
            inverseJoinColumns = @JoinColumn(name = "proyecto_id", referencedColumnName = "proyecto_id"))
    private List<Proyecto> listaDeProyectos;
}
