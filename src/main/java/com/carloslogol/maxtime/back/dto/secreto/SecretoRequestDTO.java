package com.carloslogol.maxtime.back.dto.secreto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SecretoRequestDTO {

    @NotBlank(message = "El perfil no puede ser vacío")
    private String perfil;

    @NotBlank(message = "El usuario no puede estar vacío")
    private String usuario;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contrasena;
}