package com.portfolio.argprograma.controllers;

import com.portfolio.argprograma.models.Nivel;
import com.portfolio.argprograma.services.NivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/nivel")
public class NivelController {

    @Autowired
    NivelService nivelService;

    @GetMapping("/all")
    public List<Nivel> getNiveles(){
        return nivelService.getNiveles();
    }

    @PostMapping("/save")
    public String saveNivel(@RequestBody Nivel nivel){

        nivelService.saveNivel(nivel);

        return "se ha guardado satisfactoriamente";

    }

    @PutMapping("/edit/{id}")
    public void updateNivel(@PathVariable Integer id, @RequestBody Nivel nivel){

        nivelService.updateNivel(id, nivel);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNivel(@PathVariable Integer id){
        nivelService.deleteNivel(id);
    }

    @GetMapping("/{id}")
    public Optional<Nivel> getOneNivel(@PathVariable Integer id) {
        return nivelService.getOneNivel(id);
    }
}
