package com.carloslogol.maxtime.back.model;

import com.carloslogol.maxtime.back.dto.perfil.PerfilRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "proyecto", nullable = false)
    private String proyecto;

    @Column(name = "tipohora", nullable = false)
    private String tipoHora;

    @Column(name = "servicio", nullable = false)
    private String servicio;

    @Column(name = "actividad", nullable = false)
    private String actividad;

    @Column(name = "horas", nullable = false)
    private Integer horas;

    @Column(name = "receso", nullable = false)
    private Float receso;

    @Column(name = "horainicio", nullable = false)
    private String horaInicio;

    public Perfil convertirDtoToPerfil(PerfilRequestDTO perfilRequestDTO) {
        Perfil temporal = new Perfil();
        temporal.nombre = perfilRequestDTO.getNombre();
        temporal.proyecto = perfilRequestDTO.getProyecto();
        temporal.tipoHora = perfilRequestDTO.getTipoHora();
        temporal.servicio = perfilRequestDTO.getServicio();
        temporal.actividad = perfilRequestDTO.getActividad();
        temporal.horas = perfilRequestDTO.getHoras();
        temporal.receso = perfilRequestDTO.getReceso();
        temporal.horaInicio = perfilRequestDTO.getHoraInicio();
        return temporal;
    }

}
