package com.carloslogol.maxtime.back.repository;

import com.carloslogol.maxtime.back.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, String> {

    Perfil findByNombre(String nombre);

}
