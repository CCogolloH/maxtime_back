package com.carloslogol.maxtime.back.dto.secreto;

import com.carloslogol.maxtime.back.model.UsuarioSecreto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecretoResponseDTO {
    private String perfil;
    private String usuarioEncriptado;
    private String contrasenaEncriptada;

    public static SecretoResponseDTO convert(UsuarioSecreto secreto){
        SecretoResponseDTO temp = new SecretoResponseDTO(
            secreto.getPerfil(),
            secreto.getUsuario(),
            secreto.getContrasena()
        );
        return temp;
    }

}