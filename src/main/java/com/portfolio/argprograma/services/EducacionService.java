package com.portfolio.argprograma.services;

import com.portfolio.argprograma.models.Educacion;
import com.portfolio.argprograma.repositorys.EducacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EducacionService {

    @Autowired
    EducacionRepository educacionRepository;

    public List<Educacion> getEducaciones() {
        return educacionRepository.findAll();
    }

    public void saveEducacion(Educacion educacion) {
        educacionRepository.save(educacion);
    }

    public Educacion updateEducacion(Integer id, Educacion educacion){
        Optional <Educacion> educacionOptional = educacionRepository.findById(id);

        educacion.setId(educacionOptional.get().getId());

        educacionRepository.save(educacion);

        return educacion;
    }

    public void deleteEducacion(int id) {
        educacionRepository.deleteById(id);
    }

    public Optional<Educacion> getOneEducacion(int id) {
        return educacionRepository.findById(id);
    }
}
