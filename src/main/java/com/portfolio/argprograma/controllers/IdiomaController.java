package com.portfolio.argprograma.controllers;

import com.portfolio.argprograma.models.Idioma;
import com.portfolio.argprograma.services.IdiomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/idioma")
@CrossOrigin
public class IdiomaController {

    @Autowired
    IdiomaService idiomaService;

    @GetMapping("/all")
    public List<Idioma> getIdiomas(){
        return idiomaService.getIdiomas();
    }

    @PostMapping("/save")
    public void saveIdioma(@RequestBody Idioma idioma){

        idiomaService.saveIdioma(idioma);


    }

    @PutMapping("/edit/{id}")
    public void updateIdioma(@PathVariable Integer id, @RequestBody Idioma idioma){

        idiomaService.updateIdioma(id, idioma);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteIdioma(@PathVariable Integer id){
        idiomaService.deleteIdioma(id);
    }

    @GetMapping("/{id}")
    public Optional<Idioma> getOneIdioma(@PathVariable Integer id) {
        return idiomaService.getOneIdioma(id);
    }
}
