package com.carloslogol.maxtime.back.dto.reporte;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidarResponseDTO {
    private String mensaje;
    private Boolean estado;

    public static ValidarResponseDTO reportado(){
        return new ValidarResponseDTO(
                "El usuario ya reporte su día en maxtime",
                true
        );
    }

    public static ValidarResponseDTO noReportado(){
        return new ValidarResponseDTO(
                "El usuario no ha reportado su día en maxtime",
                false
        );
    }
}
