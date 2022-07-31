package com.portfolio.argprograma.controllers;

import com.portfolio.argprograma.models.Proyecto;
import com.portfolio.argprograma.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/proyecto")
@CrossOrigin
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @GetMapping("/all")
    public List<Proyecto> getProyectos(){
        return proyectoService.getProyectos();
    }

    @PostMapping("/save")
    public void saveProyecto(@RequestBody Proyecto proyecto){

        proyectoService.saveProyecto(proyecto);

    }

    @PutMapping("/edit/{id}")
    public void updateProyecto(@PathVariable Integer id, @RequestBody Proyecto proyecto){

        proyectoService.updateProyecto(id, proyecto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteProyecto(@PathVariable Integer id){
        proyectoService.deleteProyecto(id);
    }

    @GetMapping("/{id}")
    public Optional<Proyecto> getOneProyecto(@PathVariable Integer id) {
        return proyectoService.getOneProyecto(id);
    }

}
