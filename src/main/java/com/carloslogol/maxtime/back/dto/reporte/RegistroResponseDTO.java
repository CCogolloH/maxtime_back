package com.carloslogol.maxtime.back.dto.reporte;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RegistroResponseDTO {
    private String mensaje;
    private String perfil;
    private Date fecha;


    public static RegistroResponseDTO convert(String mensaje, String perfil, Date fecha){
        RegistroResponseDTO temp = new RegistroResponseDTO(mensaje, perfil, fecha);
        return temp;
    }
}
