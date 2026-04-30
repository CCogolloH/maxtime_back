package com.carloslogol.maxtime.back.service;

import com.carloslogol.maxtime.back.dto.secreto.SecretoRequestDTO;
import com.carloslogol.maxtime.back.dto.secreto.SecretoResponseDTO;
import com.carloslogol.maxtime.back.model.UsuarioSecreto;

public interface SecretoService {

    SecretoResponseDTO saveSecreto(SecretoRequestDTO request);

    UsuarioSecreto obtenerPorPerfil(String perfil);

}
