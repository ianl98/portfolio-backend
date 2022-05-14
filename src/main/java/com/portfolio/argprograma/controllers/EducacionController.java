package com.portfolio.argprograma.controllers;

import com.portfolio.argprograma.models.Educacion;
import com.portfolio.argprograma.services.EducacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/educacion")
public class EducacionController {

    @Autowired
    EducacionService educacionService;

    @GetMapping("/all")
    public List<Educacion> getEducaciones(){
        return educacionService.getEducaciones();
    }

    @PostMapping("/save")
    public String saveEducacion(@RequestBody Educacion educacion){

        educacionService.saveEducacion(educacion);

        return "se ha guardado satisfactoriamente";

    }

    @PutMapping("/edit/{id}")
    public void updateEducacion(@PathVariable Integer id, @RequestBody Educacion educacion){

        educacionService.updateEducacion(id, educacion);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEducacion(@PathVariable Integer id){
        educacionService.deleteEducacion(id);
    }

    @GetMapping("/{id}")
    public Optional<Educacion> getOneEducacion(@PathVariable Integer id) {
        return educacionService.getOneEducacion(id);
    }
}
