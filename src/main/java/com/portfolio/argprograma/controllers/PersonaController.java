package com.portfolio.argprograma.controllers;

import com.portfolio.argprograma.models.Persona;
import com.portfolio.argprograma.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/all")
    public List<Persona> getPersonas(){
        return personaService.getPersonas();
    }

    @PostMapping("/save")
    public String savePersona(@RequestBody Persona persona){

        personaService.savePersona(persona);

        return "se ha guardado satisfactoriamente";

    }

    @PutMapping("/edit/{id}")
    public void updatePersona(@PathVariable Integer id, @RequestBody Persona persona){

        personaService.updatePersona(id, persona);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePersona(@PathVariable Integer id){
        personaService.deletePersona(id);
    }

    @GetMapping("/{id}")
    public Optional<Persona> getOnePersona(@PathVariable Integer id) {
        return personaService.getOnePersona(id);
    }
}
