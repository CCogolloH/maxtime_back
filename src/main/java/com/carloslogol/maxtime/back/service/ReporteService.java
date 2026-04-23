package com.carloslogol.maxtime.back.service;

import com.carloslogol.maxtime.back.dto.reporte.RegistroRequestDTO;
import com.carloslogol.maxtime.back.model.Reporte;

import java.util.Date;

public interface ReporteService {

    Reporte registrarReporte(RegistroRequestDTO registroRequestDTO);

    Reporte obtenerPorPerfilyFecha(String perfil, Date fecha);

}
