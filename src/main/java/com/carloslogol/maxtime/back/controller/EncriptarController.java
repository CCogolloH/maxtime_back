package com.carloslogol.maxtime.back.controller;

import com.carloslogol.maxtime.back.dto.secreto.SecretoRequestDTO;
import com.carloslogol.maxtime.back.dto.secreto.SecretoResponseDTO;
import com.carloslogol.maxtime.back.service.SecretoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secreto/encriptar")
@RequiredArgsConstructor
public class EncriptarController {

    private final SecretoService secretoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SecretoResponseDTO save(@Valid @RequestBody SecretoRequestDTO request) {
        return secretoService.saveSecreto(request);
    }
}