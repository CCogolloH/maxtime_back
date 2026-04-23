package com.carloslogol.maxtime.back.dto.perfil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PerfilResponseDTO {

    private String mensaje;
    private Long id;

    public static PerfilResponseDTO creado(long id) {
        return new PerfilResponseDTO("Usuario creado exitosamente", id);
    }

}
