package com.carloslogol.maxtime.back.dto.reporte;

import com.carloslogol.maxtime.back.model.Reporte;
import lombok.Data;

@Data
public class ServicioResponseDTO {

    private String tipoHora;
    private String horaInicio;
    private String actividad;
    private String servicio;

    public static ServicioResponseDTO convert(Reporte reporte){
        ServicioResponseDTO temp = new ServicioResponseDTO();

        temp.tipoHora = reporte.getTipoHora();
        temp.horaInicio = reporte.getHoraInicio();
        temp.actividad = reporte.getActividad();
        temp.servicio = reporte.getServicio();

        return temp;
    }
}
