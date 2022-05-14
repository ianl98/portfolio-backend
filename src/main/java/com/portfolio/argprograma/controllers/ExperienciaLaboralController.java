package com.portfolio.argprograma.controllers;


import com.portfolio.argprograma.models.ExperienciaLaboral;
import com.portfolio.argprograma.services.ExperienciaLaboralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/experienciaLaboral")
public class ExperienciaLaboralController {

    @Autowired
    ExperienciaLaboralService experienciaLaboralService;

    @GetMapping("/all")
    public List<ExperienciaLaboral> getExperienciasLaborales(){
        return experienciaLaboralService.getExperienciasLaborales();
    }

    @PostMapping("/save")
    public String saveExperienciaLaboral(@RequestBody ExperienciaLaboral experienciaLaboral){

        experienciaLaboralService.saveExperienciaLaboral(experienciaLaboral);

        return "se ha guardado satisfactoriamente";

    }

    @PutMapping("/edit/{id}")
    public void updateExperienciaLaboral(@PathVariable Integer id, @RequestBody ExperienciaLaboral experienciaLaboral){

        experienciaLaboralService.updateExperienciaLaboral(id, experienciaLaboral);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExperienciaLaboral(@PathVariable Integer id){
        experienciaLaboralService.deleteExperienciaLaboral(id);
    }

    @GetMapping("/{id}")
    public Optional<ExperienciaLaboral> getOneExperienciaLaboral(@PathVariable Integer id) {
        return experienciaLaboralService.getOneExperienciaLaboral(id);
    }
}
