package com.portfolio.argprograma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.argprograma.models.ExperienciaLaboral;
import com.portfolio.argprograma.models.Persona;
import com.portfolio.argprograma.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("api/persona")
@CrossOrigin
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/all")
    public List<Persona> getPersonas(){
        return personaService.getPersonas();
    }

    @PostMapping("/save")
    public void savePersona(@RequestBody Persona persona){

        personaService.savePersona(persona);

    }

    @GetMapping("/getImage/{id}")
    public byte[] getImagePersona(@PathVariable int id) throws IOException {

        Optional <Persona> personaOptional = personaService.getOnePersona(id);

        return Files.readAllBytes(Paths.get("src//main//resources//static/persona/"+personaOptional.get().getFotoPerfil()));
    }

    @PutMapping("/edit/{id}")
    public void updatePersona(@PathVariable Integer id,@RequestParam(value = "file", required = false) MultipartFile foto, @RequestParam("persona") String persona) throws JsonProcessingException {

        Persona per = new ObjectMapper().readValue(persona, Persona.class);

        Optional <Persona> personaOptional = personaService.getOnePersona(id);

        if (foto != null){
            Path directorioImagenes = Paths.get("src//main//resources//static/persona");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            Random rand = new Random();
            int int_random = rand.nextInt(2000);

            String nombreFoto = foto.getOriginalFilename(); //cosa.jpg
            String [] partes = nombreFoto.split("\\.");//["cosa", ".jpg"]
            String nombreCompleto = partes[0] + int_random + "." + partes[1];
            per.setFotoPerfil(nombreCompleto);
            try {
                byte[] bytesImg = foto.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreCompleto);
                Files.write(rutaCompleta, bytesImg);
            } catch (IOException e){
                e.printStackTrace();
            }

            try {
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + personaOptional.get().getFotoPerfil());
                Files.delete(rutaCompleta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            per.setFotoPerfil(personaOptional.get().getFotoPerfil());
        }

        personaService.updatePersona(id, per);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deletePersona(@PathVariable Integer id){
        personaService.deletePersona(id);
    }

    @GetMapping("/{id}")
    public Optional<Persona> getOnePersona(@PathVariable Integer id) {
        return personaService.getOnePersona(id);
    }
}
