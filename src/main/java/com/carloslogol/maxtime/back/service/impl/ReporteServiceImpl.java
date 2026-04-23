package com.carloslogol.maxtime.back.service.impl;

import com.carloslogol.maxtime.back.dto.reporte.RegistroRequestDTO;
import com.carloslogol.maxtime.back.model.Perfil;
import com.carloslogol.maxtime.back.model.Reporte;
import com.carloslogol.maxtime.back.repository.PerfilRepository;
import com.carloslogol.maxtime.back.repository.ReporteRepository;
import com.carloslogol.maxtime.back.service.ReporteService;
import com.carloslogol.maxtime.back.utils.FechaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final ReporteRepository reporteRepository;
    private final PerfilRepository perfilRepository;

    @Autowired
    public ReporteServiceImpl(ReporteRepository reporteRepository, PerfilRepository perfilRepository){
        this.reporteRepository = reporteRepository;
        this.perfilRepository = perfilRepository;
    }

    @Override
    public Reporte registrarReporte(RegistroRequestDTO registroRequestDTO) {
        Perfil perfil = perfilRepository.findByNombre(registroRequestDTO.getPerfil());
        Reporte reporte = new Reporte();

        reporte.setProyecto(perfil.getProyecto());
        reporte.setTipoHora(perfil.getTipoHora());
        reporte.setHoraInicio(perfil.getHoraInicio());
        reporte.setActividad(perfil.getActividad());
        reporte.setServicio(perfil.getServicio());
        reporte.setFecha(registroRequestDTO.getFecha());
        reporte.setReceso(perfil.getReceso());
        reporte.setEstado("Pendiente");
        reporte.setPerfil(registroRequestDTO.getPerfil());
        reporte.setObservacion("");

        if (FechaUtil.esViernes()) reporte.setHoras(perfil.getHoras()-1);
        else reporte.setHoras(perfil.getHoras());

        return reporteRepository.save(reporte);
    }

    @Override
    public Reporte obtenerPorPerfilyFecha(String perfil, Date fecha) {
        return reporteRepository.findByPerfilAndFecha(perfil, fecha);
    }
}
