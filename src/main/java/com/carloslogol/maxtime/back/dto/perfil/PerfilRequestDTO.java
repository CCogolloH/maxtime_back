package com.carloslogol.maxtime.back.dto.perfil;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PerfilRequestDTO {

    @NotBlank(message = "El nombre no puede estar vació")
    private String nombre;

    @NotBlank(message = "El nombre del proyecto no puede estar vació")
    private String proyecto;

    @NotBlank(message = "El tipo de Hora no puede estar vació")
    private String tipoHora;

    @NotBlank(message = "El servicio no puede esta vació")
    private String servicio;

    @NotBlank(message = "La actividad no puede estar vaciá")
    private String actividad;

    @NotNull(message = "Las horas no pueden estar vacías")
    @Min(value = 1, message = "Las horas deben ser mínimo 1")
    private Integer horas;

    @NotNull(message = "Las horas no pueden estar vacías")
    @Min(value = 1, message = "Las horas deben ser mínimo 1")
    private Float receso;

    @NotBlank(message = "La hora inicio no puede estar vacía")
    private String horaInicio;

}
