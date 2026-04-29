package com.carloslogol.maxtime.back.controller;

import com.carloslogol.maxtime.back.dto.reporte.*;
import com.carloslogol.maxtime.back.model.Reporte;
import com.carloslogol.maxtime.back.service.ReporteService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    private final ReporteService reporteService;

    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @PostConstruct
    public void init(){
        System.out.println("ReporteController cargado correctamente");
    }

    @GetMapping("/servicio")
    public ResponseEntity<ServicioResponseDTO> consultarServicioPorPerfil(@RequestParam String perfil, String fecha){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(fecha, formatter);

        Date fecha2 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Reporte reporte = reporteService.obtenerPorPerfilyFecha(perfil, fecha2);
        if(reporte == null) throw new RuntimeException("No se encontro este perfil: " + perfil);

        return ResponseEntity.ok(ServicioResponseDTO.convert(reporte));
    }

    @GetMapping("/proyecto")
    public ResponseEntity<ProyectoResponseDTO> consultarProyectoPorPerfil(@RequestParam String perfil, String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(fecha, formatter);

        Date fecha2 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Reporte reporte = reporteService.obtenerPorPerfilyFecha(perfil, fecha2);

        return ResponseEntity.ok(ProyectoResponseDTO.convert(reporte));

    }

    @PostMapping("/registro_por_perfil")
    public ResponseEntity<RegistroResponseDTO> registroPorPerfil(@Valid @RequestBody RegistroRequestDTO registroRequestDTO){
        Reporte reporte = reporteService.registrarReporte(registroRequestDTO);
        if(reporte == null) {
            throw new RuntimeException("No se encontro este perfil" + registroRequestDTO.getPerfil());
        }
        return ResponseEntity.ok(RegistroResponseDTO.convert("Registro completo", registroRequestDTO.getPerfil(), registroRequestDTO.getFecha()));
    }

    @GetMapping("/validar")
    public ResponseEntity<ValidarResponseDTO> validarReporte(
            @RequestParam String perfil,
            @RequestParam String fecha) {

        LocalDate localDate = LocalDate.parse(fecha);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Reporte reporte = reporteService.obtenerPorPerfilyFecha(perfil, date);

        if (reporte == null) {
            return ResponseEntity.ok(ValidarResponseDTO.noReportado());
        } else {
            return ResponseEntity.ok(ValidarResponseDTO.reportado());
        }
    }

}
