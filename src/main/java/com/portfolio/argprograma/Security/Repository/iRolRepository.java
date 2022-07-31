package com.portfolio.argprograma.Security.Repository;

import com.portfolio.argprograma.Security.Entitys.Rol;
import com.portfolio.argprograma.Security.Enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
