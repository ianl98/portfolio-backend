package com.portfolio.argprograma.repositorys;

import com.portfolio.argprograma.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository <Persona, Integer> {
}
