package com.portfolio.argprograma.services;

import com.portfolio.argprograma.models.Proyecto;
import com.portfolio.argprograma.models.Tecnologia;
import com.portfolio.argprograma.repositorys.ProyectoRepository;
import com.portfolio.argprograma.repositorys.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TecnologiaService {

    @Autowired
    TecnologiaRepository tecnologiaRepository;


    public ResponseEntity<List<Tecnologia>> getTecnologias(){
        return ResponseEntity.ok(tecnologiaRepository.findAll());
    }

    public ResponseEntity<Tecnologia> saveTecnologia(Tecnologia tecnologia){
        return new ResponseEntity<>(tecnologiaRepository.save(tecnologia), HttpStatus.CREATED);
    }


    public ResponseEntity<Tecnologia> updateTecnologia(Integer id, Tecnologia tecnologia){

        Optional<Tecnologia> tecnologiaOptional = tecnologiaRepository.findById(id);

        if (!tecnologiaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        tecnologia.setId(tecnologiaOptional.get().getId());

        tecnologiaRepository.save(tecnologia);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Tecnologia> deleteTecnologia(Integer id){
        Optional<Tecnologia> tecnologiaOptional = tecnologiaRepository.findById(id);

        if (!tecnologiaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        tecnologiaRepository.delete(tecnologiaOptional.get());

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Tecnologia> getOneTecnologia(Integer id){
        Optional<Tecnologia> tecnologiaOptional = tecnologiaRepository.findById(id);

        if (!tecnologiaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(tecnologiaOptional.get());
    }

    public ResponseEntity<List<Proyecto>> getProyectos(Integer id){
        Tecnologia tecnologia = tecnologiaRepository.findById(id).orElseThrow();

        if (tecnologia != null){
            return new ResponseEntity<>(tecnologia.getListaDeProyectos(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
