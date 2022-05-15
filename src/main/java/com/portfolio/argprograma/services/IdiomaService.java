package com.portfolio.argprograma.services;


import com.portfolio.argprograma.models.Idioma;
import com.portfolio.argprograma.repositorys.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IdiomaService {

    @Autowired
    IdiomaRepository idiomaRepository;

    public List<Idioma> getIdiomas() {
        return idiomaRepository.findAll();
    }

    public void saveIdioma(Idioma idioma) {
        idiomaRepository.save(idioma);
    }

    public Idioma updateIdioma(Integer id, Idioma idioma){
        Optional<Idioma> idiomaOptional = idiomaRepository.findById(id);

        idioma.setId(idiomaOptional.get().getId());

        idiomaRepository.save(idioma);

        return idioma;
    }

    public void deleteIdioma(int id) {
        idiomaRepository.deleteById(id);
    }

    public Optional<Idioma> getOneIdioma(int id) {
        return idiomaRepository.findById(id);
    }
}
