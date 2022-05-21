package com.portfolio.argprograma.services;

import com.portfolio.argprograma.models.Habilidad;
import com.portfolio.argprograma.repositorys.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HabilidadService {

    @Autowired
    HabilidadRepository habilidadRepository;

    public List<Habilidad> getHabilidades() {
        return habilidadRepository.findAll();
    }

    public void saveHabilidad(Habilidad habilidad) {
        habilidadRepository.save(habilidad);
    }

    public Habilidad updateHabilidad(Integer id, Habilidad habilidad){
        Optional<Habilidad> habilidadOptional = habilidadRepository.findById(id);

        habilidad.setId(habilidadOptional.get().getId());

        habilidadRepository.save(habilidad);

        return habilidad;
    }

    public void deleteHabilidad(int id) {
        habilidadRepository.deleteById(id);
    }

    public Optional<Habilidad> getOneHabilidad(int id) {
        return habilidadRepository.findById(id);
    }
}
