package com.carloslogol.maxtime.back.controller;

import com.carloslogol.maxtime.back.dto.secreto.SecretoRequestDTO;
import com.carloslogol.maxtime.back.dto.secreto.SecretoResponseDTO;
import com.carloslogol.maxtime.back.model.UsuarioSecreto;
import com.carloslogol.maxtime.back.service.SecretoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secreto")
@RequiredArgsConstructor
public class SecretoController {

    private final SecretoService secretoService;

    @PostMapping("/encriptar")
    @ResponseStatus(HttpStatus.CREATED)
    public SecretoResponseDTO save(@Valid @RequestBody SecretoRequestDTO request) {
        return secretoService.saveSecreto(request);
    }

    @GetMapping("/consultar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SecretoResponseDTO> consultarPorPerfil(@RequestParam String perfil) {

        UsuarioSecreto usuarioSecreto = secretoService.obtenerPorPerfil(perfil);

        return ResponseEntity.ok(SecretoResponseDTO.convert(usuarioSecreto));

    }

}