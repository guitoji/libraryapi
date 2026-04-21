package com.guitoji.libraryapi.controller;

import com.guitoji.libraryapi.controller.dto.CadastroLivroDTO;
import com.guitoji.libraryapi.controller.dto.ErroResposta;
import com.guitoji.libraryapi.controller.mappers.LivroMapper;
import com.guitoji.libraryapi.exceptions.RegistroDuplicadoException;
import com.guitoji.libraryapi.model.Livro;
import com.guitoji.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        Livro livro = mapper.toEntity(dto);
        service.salvarLivro(livro);
        URI location = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(location).build();
    }
}
