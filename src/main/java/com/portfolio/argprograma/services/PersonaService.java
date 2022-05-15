package com.portfolio.argprograma.services;

import com.portfolio.argprograma.models.Persona;
import com.portfolio.argprograma.repositorys.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    public List<Persona> getPersonas() {
        return personaRepository.findAll();
    }

    public void savePersona(Persona persona) {
        personaRepository.save(persona);
    }

    public Persona updatePersona(Integer id, Persona persona){
        Optional<Persona> personaOptional = personaRepository.findById(id);

        persona.setId(personaOptional.get().getId());

        personaRepository.save(persona);

        return persona;
    }

    public void deletePersona(int id) {
        personaRepository.deleteById(id);
    }

    public Optional<Persona> getOnePersona(int id) {
        return personaRepository.findById(id);
    }
}
