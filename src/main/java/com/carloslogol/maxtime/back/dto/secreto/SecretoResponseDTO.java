package com.carloslogol.maxtime.back.dto.secreto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecretoResponseDTO {
    private String name;
    private String usuarioEncriptado;
    private String contrasenaEncriptada;
}