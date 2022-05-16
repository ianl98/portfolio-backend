package com.portfolio.argprograma.services;

import com.portfolio.argprograma.models.Proyecto;
import com.portfolio.argprograma.repositorys.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    ProyectoRepository proyectoRepository;

    public List<Proyecto> getProyectos() {
        return proyectoRepository.findAll();
    }

    public void saveProyecto(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    public Proyecto updateProyecto(Integer id, Proyecto proyecto){
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(id);

        proyecto.setId(proyectoOptional.get().getId());

        proyectoRepository.save(proyecto);

        return proyecto;
    }

    public void deleteProyecto(int id) {
        proyectoRepository.deleteById(id);
    }

    public Optional<Proyecto> getOneProyecto(int id) {
        return proyectoRepository.findById(id);
    }

}
