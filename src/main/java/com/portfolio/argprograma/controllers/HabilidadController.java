package com.portfolio.argprograma.controllers;

import com.portfolio.argprograma.models.Habilidad;
import com.portfolio.argprograma.services.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/habilidad")
@CrossOrigin
public class HabilidadController {

    @Autowired
    HabilidadService habilidadService;

    @GetMapping("/all")
    public List<Habilidad> getHabilidades(){
        return habilidadService.getHabilidades();
    }

    @PostMapping("/save")
    public void saveHabilidad(@RequestBody Habilidad habilidad){

        habilidadService.saveHabilidad(habilidad);

    }

    @PutMapping("/edit/{id}")
    public void updateHabilidad(@PathVariable Integer id, @RequestBody Habilidad habilidad){

        habilidadService.updateHabilidad(id, habilidad);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteHabilidad(@PathVariable Integer id){
        habilidadService.deleteHabilidad(id);
    }

    @GetMapping("/{id}")
    public Optional<Habilidad> getOneHabilidad(@PathVariable Integer id) {
        return habilidadService.getOneHabilidad(id);
    }
}
