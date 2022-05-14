package com.portfolio.argprograma.services;

import com.portfolio.argprograma.models.Educacion;
import com.portfolio.argprograma.models.ExperienciaLaboral;
import com.portfolio.argprograma.repositorys.ExperienciaLaboralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienciaLaboralService {

    @Autowired
    ExperienciaLaboralRepository experienciaLaboralRepository;

    public List<ExperienciaLaboral> getExperienciasLaborales() {
        return experienciaLaboralRepository.findAll();
    }

    public void saveExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
        experienciaLaboralRepository.save(experienciaLaboral);
    }

    public ExperienciaLaboral updateExperienciaLaboral(Integer id, ExperienciaLaboral experienciaLaboral){
        Optional<ExperienciaLaboral> experienciaLaboralOptional = experienciaLaboralRepository.findById(id);

        experienciaLaboral.setId(experienciaLaboralOptional.get().getId());

        experienciaLaboralRepository.save(experienciaLaboral);

        return experienciaLaboral;
    }

    public void deleteExperienciaLaboral(int id) {
        experienciaLaboralRepository.deleteById(id);
    }

    public Optional<ExperienciaLaboral> getOneExperienciaLaboral(int id) {
        return experienciaLaboralRepository.findById(id);
    }
}
