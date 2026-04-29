package com.carloslogol.maxtime.back.dto.reporte;

import com.carloslogol.maxtime.back.model.Reporte;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProyectoResponseDTO {

    private String proyecto;

    public static ProyectoResponseDTO convert(Reporte reporte){
        ProyectoResponseDTO temp = new ProyectoResponseDTO(reporte.getProyecto());
        return temp;
    }

}
