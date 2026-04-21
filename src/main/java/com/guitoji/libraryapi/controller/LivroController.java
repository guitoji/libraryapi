package com.guitoji.libraryapi.controller;

import com.guitoji.libraryapi.controller.dto.CadastroLivroDTO;
import com.guitoji.libraryapi.controller.dto.ErroResposta;
import com.guitoji.libraryapi.exceptions.RegistroDuplicadoException;
import com.guitoji.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid CadastroLivroDTO dto) {
        try {

            return ResponseEntity.ok(dto);
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
