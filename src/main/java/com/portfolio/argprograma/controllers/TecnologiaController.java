package com.portfolio.argprograma.controllers;

import com.portfolio.argprograma.models.Proyecto;
import com.portfolio.argprograma.models.Tecnologia;
import com.portfolio.argprograma.services.TecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/tecnologia")
public class TecnologiaController {

    @Autowired
    TecnologiaService tecnologiaService;

    @GetMapping("/all")
    public ResponseEntity<List<Tecnologia>> getTecnologias(){
        return tecnologiaService.getTecnologias();
    }

    @PostMapping("/save")
    public String saveTecnologia(@RequestBody Tecnologia tecnologia){

        tecnologiaService.saveTecnologia(tecnologia);

        return "se ha guardado satisfactoriamente";

    }

    @PutMapping("/edit/{id}")
    public void updateTecnologia(@PathVariable Integer id, @RequestBody Tecnologia tecnologia){

        tecnologiaService.updateTecnologia(id, tecnologia);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTecnologia(@PathVariable Integer id){
        tecnologiaService.deleteTecnologia(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnologia> getOneTecnologia(@PathVariable Integer id) {
        return tecnologiaService.getOneTecnologia(id);
    }

    @GetMapping("/proyectos/{id}")
    public ResponseEntity<List<Proyecto>> getProyectos(@PathVariable Integer id){
        return tecnologiaService.getProyectos(id);
    }
}
