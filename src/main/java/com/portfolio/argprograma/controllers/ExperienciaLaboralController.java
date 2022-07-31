package com.portfolio.argprograma.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.argprograma.models.Educacion;
import com.portfolio.argprograma.models.ExperienciaLaboral;
import com.portfolio.argprograma.services.ExperienciaLaboralService;
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
@RequestMapping("api/experienciaLaboral")
@CrossOrigin
public class ExperienciaLaboralController {

    @Autowired
    ExperienciaLaboralService experienciaLaboralService;

    @GetMapping("/all")
    public List<ExperienciaLaboral> getExperienciasLaborales(){
        return experienciaLaboralService.getExperienciasLaborales();
    }

    @PostMapping("/save")
    public void saveExperienciaLaboral(@RequestParam("file") MultipartFile logo, @RequestParam("experienciaLaboral") String experienciaLaboral) throws JsonProcessingException{

        ExperienciaLaboral exp = new ObjectMapper().readValue(experienciaLaboral, ExperienciaLaboral.class);

        if (!logo.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static/expLaboral");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            Random rand = new Random();
            int int_random = rand.nextInt(2000);

            String nombreFoto = logo.getOriginalFilename(); //cosa.jpg
            String [] partes = nombreFoto.split("\\.");//["cosa", ".jpg"]
            String nombreCompleto = partes[0] + int_random + "." + partes[1];
            exp.setLogo(nombreCompleto);
            try {
                byte[] bytesImg = logo.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreCompleto);
                Files.write(rutaCompleta, bytesImg);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        experienciaLaboralService.saveExperienciaLaboral(exp);

    }

    @GetMapping("/getImage/{id}")
    public byte[] getImageEducacion(@PathVariable int id) throws IOException {
        Optional<ExperienciaLaboral> experienciaLaboral = experienciaLaboralService.getOneExperienciaLaboral(id);

        return Files.readAllBytes(Paths.get("src//main//resources//static/expLaboral/"+experienciaLaboral.get().getLogo()));
    }

    @PutMapping("/edit/{id}")
    public void updateExperienciaLaboral(@PathVariable Integer id,@RequestParam(value = "file", required = false) MultipartFile logo, @RequestParam("experienciaLaboral") String experienciaLaboral) throws JsonProcessingException{

        ExperienciaLaboral exp = new ObjectMapper().readValue(experienciaLaboral, ExperienciaLaboral.class);

        Optional <ExperienciaLaboral> experienciaLaboralOptional = experienciaLaboralService.getOneExperienciaLaboral(id);

        if (logo != null){
            Path directorioImagenes = Paths.get("src//main//resources//static/expLaboral");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            Random rand = new Random();
            int int_random = rand.nextInt(2000);

            String nombreFoto = logo.getOriginalFilename(); //cosa.jpg
            String [] partes = nombreFoto.split("\\.");//["cosa", ".jpg"]
            String nombreCompleto = partes[0] + int_random + "." + partes[1];
            exp.setLogo(nombreCompleto);
            try {
                byte[] bytesImg = logo.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreCompleto);
                Files.write(rutaCompleta, bytesImg);
            } catch (IOException e){
                e.printStackTrace();
            }

            try {
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + experienciaLaboralOptional.get().getLogo());
                Files.delete(rutaCompleta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            exp.setLogo(experienciaLaboralOptional.get().getLogo());
        }

        experienciaLaboralService.updateExperienciaLaboral(id, exp);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteExperienciaLaboral(@PathVariable Integer id){
        experienciaLaboralService.deleteExperienciaLaboral(id);
    }

    @GetMapping("/{id}")
    public Optional<ExperienciaLaboral> getOneExperienciaLaboral(@PathVariable Integer id) {
        return experienciaLaboralService.getOneExperienciaLaboral(id);
    }
}
