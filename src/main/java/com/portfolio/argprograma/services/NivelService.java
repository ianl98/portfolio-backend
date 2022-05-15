package com.portfolio.argprograma.services;

import com.portfolio.argprograma.models.Nivel;
import com.portfolio.argprograma.repositorys.NivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NivelService {

    @Autowired
    NivelRepository nivelRepository;

    public List<Nivel> getNiveles() {
        return nivelRepository.findAll();
    }

    public void saveNivel(Nivel nivel) {
        nivelRepository.save(nivel);
    }

    public Nivel updateNivel(Integer id, Nivel nivel){
        Optional<Nivel> nivelOptional = nivelRepository.findById(id);

        nivel.setId(nivelOptional.get().getId());

        nivelRepository.save(nivel);

        return nivel;
    }

    public void deleteNivel(int id) {
        nivelRepository.deleteById(id);
    }

    public Optional<Nivel> getOneNivel(int id) {
        return nivelRepository.findById(id);
    }
}
