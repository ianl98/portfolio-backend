package com.portfolio.argprograma.controllers;

import com.portfolio.argprograma.models.Proyecto;
import com.portfolio.argprograma.models.Tecnologia;
import com.portfolio.argprograma.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/proyecto")
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;


    @GetMapping("/all")
    public List<Proyecto> getProyectos(){
        return proyectoService.getProyectos();
    }

    @PostMapping("/save")
    public String saveProyecto(@RequestBody Proyecto proyecto){

        proyectoService.saveProyecto(proyecto);

        return "se ha guardado satisfactoriamente";

    }

    @PutMapping("/edit/{id}")
    public void updateProyecto(@PathVariable Integer id, @RequestBody Proyecto proyecto){

        proyectoService.updateProyecto(id, proyecto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProyecto(@PathVariable Integer id){
        proyectoService.deleteProyecto(id);
    }

    @GetMapping("/{id}")
    public Optional<Proyecto> getOneProyecto(@PathVariable Integer id) {
        return proyectoService.getOneProyecto(id);
    }

    @GetMapping("tecnologias/{id}")
    public ResponseEntity<List<Tecnologia>> getTecnologias(Integer id){
        return proyectoService.getTecnologias(id);
    }
}
