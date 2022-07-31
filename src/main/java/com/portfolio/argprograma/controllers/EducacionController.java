package com.portfolio.argprograma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.argprograma.models.Educacion;
import com.portfolio.argprograma.services.EducacionService;
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
@RequestMapping("api/educacion")
@CrossOrigin
public class EducacionController {

    @Autowired
    EducacionService educacionService;

    @GetMapping("/all")
    public List<Educacion> getEducaciones(){
        return educacionService.getEducaciones();
    }

    @PostMapping("/upload")
    public void uploadImage(@RequestPart(value = "file") MultipartFile foto){

        if (!foto.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static/educacion");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = foto.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + foto.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    @PostMapping("/save")
    public void saveEducacion(@RequestParam("file") MultipartFile foto, @RequestParam("educacion") String educacion) throws JsonProcessingException {

        Educacion edu = new ObjectMapper().readValue(educacion, Educacion.class);

        if (!foto.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static/educacion");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            Random rand = new Random();
            int int_random = rand.nextInt(2000);

            String nombreFoto = foto.getOriginalFilename(); //cosa.jpg
            String [] partes = nombreFoto.split("\\.");//["cosa", ".jpg"]
            String nombreCompleto = partes[0] + int_random + "." + partes[1];
            edu.setFoto(nombreCompleto);
            try {
                byte[] bytesImg = foto.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreCompleto);
                Files.write(rutaCompleta, bytesImg);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        educacionService.saveEducacion(edu);


    }

    @GetMapping("/getImage/{id}")
    public byte[] getImageEducacion(@PathVariable int id) throws IOException {
        Optional<Educacion> educacion = educacionService.getOneEducacion(id);

        return Files.readAllBytes(Paths.get("src//main//resources//static/educacion/"+educacion.get().getFoto()));
    }

    @PutMapping("/edit/{id}")
    public void updateEducacion(@PathVariable Integer id,@RequestParam(value = "file", required = false) MultipartFile foto, @RequestParam("educacion") String educacion) throws JsonProcessingException{

        Educacion edu = new ObjectMapper().readValue(educacion, Educacion.class);

        Optional <Educacion> educacionOptional = educacionService.getOneEducacion(id);

        if (foto != null){
            Path directorioImagenes = Paths.get("src//main//resources//static/educacion");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            Random rand = new Random();
            int int_random = rand.nextInt(2000);

            String nombreFoto = foto.getOriginalFilename(); //cosa.jpg
            String [] partes = nombreFoto.split("\\.");//["cosa", ".jpg"]
            String nombreCompleto = partes[0] + int_random + "." + partes[1];
            edu.setFoto(nombreCompleto);
            try {
                byte[] bytesImg = foto.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreCompleto);
                Files.write(rutaCompleta, bytesImg);
            } catch (IOException e){
                e.printStackTrace();
            }

            try {
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + educacionOptional.get().getFoto());
                Files.delete(rutaCompleta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            edu.setFoto(educacionOptional.get().getFoto());
        }



        educacionService.updateEducacion(id, edu);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteEducacion(@PathVariable Integer id){
        educacionService.deleteEducacion(id);
    }

    @GetMapping("/{id}")
    public Optional<Educacion> getOneEducacion(@PathVariable Integer id) {
        return educacionService.getOneEducacion(id);
    }
}
