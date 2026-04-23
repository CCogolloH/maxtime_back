package com.carloslogol.maxtime.back.dto.reporte;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class RegistroRequestDTO {

    @NotBlank(message = "El perfil no existe")
    private String perfil;

    @NotNull(message = "No puede estar nulo")
    private Date fecha;

}
