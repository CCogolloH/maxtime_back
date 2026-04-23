package com.carloslogol.maxtime.back.service.impl;

import com.carloslogol.maxtime.back.model.Perfil;
import com.carloslogol.maxtime.back.repository.PerfilRepository;
import com.carloslogol.maxtime.back.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;

    @Autowired
    public PerfilServiceImpl(PerfilRepository perfilRepository){
        this.perfilRepository = perfilRepository;
    }

    @Override
    public Perfil registrarPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @Override
    public Perfil obtenerPorNombre(String nombre) {
        return perfilRepository.findByNombre(nombre);
    }
}
