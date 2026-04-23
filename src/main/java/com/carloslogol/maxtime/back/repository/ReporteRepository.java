package com.carloslogol.maxtime.back.repository;

import com.carloslogol.maxtime.back.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ReporteRepository extends JpaRepository<Reporte, String> {
    Reporte findByPerfilAndFecha(String perfil, Date fecha);
}
