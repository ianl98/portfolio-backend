package com.portfolio.argprograma.repositorys;

import com.portfolio.argprograma.models.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository <Proyecto, Integer> {
}
