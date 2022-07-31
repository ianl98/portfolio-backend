package com.portfolio.argprograma.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter @Setter
@Entity
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String institucion;
    private String carrera;
    private String foto;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date inicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date fin;
}
