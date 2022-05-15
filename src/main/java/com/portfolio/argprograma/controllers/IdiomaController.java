package com.portfolio.argprograma.controllers;

import com.portfolio.argprograma.models.Idioma;
import com.portfolio.argprograma.services.IdiomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/idioma")
public class IdiomaController {

    @Autowired
    IdiomaService idiomaService;

    @GetMapping("/all")
    public List<Idioma> getIdiomas(){
        return idiomaService.getIdiomas();
    }

    @PostMapping("/save")
    public String saveIdioma(@RequestBody Idioma idioma){

        idiomaService.saveIdioma(idioma);

        return "se ha guardado satisfactoriamente";

    }

    @PutMapping("/edit/{id}")
    public void updateIdioma(@PathVariable Integer id, @RequestBody Idioma idioma){

        idiomaService.updateIdioma(id, idioma);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteIdioma(@PathVariable Integer id){
        idiomaService.deleteIdioma(id);
    }

    @GetMapping("/{id}")
    public Optional<Idioma> getOneIdioma(@PathVariable Integer id) {
        return idiomaService.getOneIdioma(id);
    }
}
