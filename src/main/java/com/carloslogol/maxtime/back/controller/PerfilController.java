package com.carloslogol.maxtime.back.controller;

import com.carloslogol.maxtime.back.dto.perfil.PerfilRequestDTO;
import com.carloslogol.maxtime.back.dto.perfil.PerfilResponseDTO;
import com.carloslogol.maxtime.back.model.Perfil;
import com.carloslogol.maxtime.back.service.PerfilService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfil")
public class PerfilController {

    private final PerfilService perfilService;

    @Autowired
    public PerfilController(PerfilService perfilService){
        this.perfilService = perfilService;
    }

    @PostConstruct
    public void init() {
        System.out.println("PerfilController cargado correctamente");
    }

    @PostMapping("/registro")
    public ResponseEntity<PerfilResponseDTO> registrar(@Valid @RequestBody PerfilRequestDTO perfilRequestDTO) {
        Perfil perfil = new Perfil();
        Perfil registrarPerfil = perfilService.registrarPerfil(perfil.convertirDtoToPerfil(perfilRequestDTO));
        return new ResponseEntity<>(PerfilResponseDTO.creado(registrarPerfil.getId()), HttpStatus.CREATED);
    }

    @GetMapping("/consulta")
    public ResponseEntity<?> consultar(@RequestParam String nombre){
        Perfil perfil = perfilService.obtenerPorNombre(nombre);
        if (perfil == null) {
            throw new RuntimeException("No se encontro este perfil" + nombre);
        }
        return ResponseEntity.ok(perfil);
    }

}
