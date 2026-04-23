package com.carloslogol.maxtime.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reportes")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipohora")
    private String tipoHora;

    @Column(name = "proyecto")
    private String proyecto;

    @Column(name = "servicio")
    private String servicio;

    @Column(name = "actividad")
    private String actividad;

    @Column(name = "horas")
    private Integer horas;

    @Column(name = "horainicio")
    private String horaInicio;

    @Column(name = "receso")
    private Float receso;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "perfil")
    private String perfil;

    @Column(name = "estado")
    private String estado;

}
