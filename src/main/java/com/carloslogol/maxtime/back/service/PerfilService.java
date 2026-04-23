package com.carloslogol.maxtime.back.service;

import com.carloslogol.maxtime.back.model.Perfil;

public interface PerfilService {

    Perfil registrarPerfil(Perfil perfil);

    Perfil obtenerPorNombre(String Nombre);

}
