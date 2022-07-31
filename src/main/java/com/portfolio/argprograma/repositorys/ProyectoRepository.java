package com.portfolio.argprograma.repositorys;

import com.portfolio.argprograma.models.Proyecto;
import org.hibernate.mapping.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface ProyectoRepository extends JpaRepository <Proyecto, Integer> {

}
