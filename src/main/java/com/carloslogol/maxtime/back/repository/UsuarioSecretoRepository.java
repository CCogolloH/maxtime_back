package com.carloslogol.maxtime.back.repository;

import com.carloslogol.maxtime.back.model.UsuarioSecreto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioSecretoRepository extends JpaRepository<UsuarioSecreto, String>  {
    UsuarioSecreto findByPerfil(String perfil);
}
