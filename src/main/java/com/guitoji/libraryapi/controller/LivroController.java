package com.guitoji.libraryapi.controller;

import com.guitoji.libraryapi.controller.dto.AutorDTO;
import com.guitoji.libraryapi.controller.dto.CadastroLivroDTO;
import com.guitoji.libraryapi.controller.dto.ErroResposta;
import com.guitoji.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import com.guitoji.libraryapi.controller.mappers.LivroMapper;
import com.guitoji.libraryapi.exceptions.RegistroDuplicadoException;
import com.guitoji.libraryapi.model.Livro;
import com.guitoji.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable String id) {
        return service
                .obterPorId(UUID.fromString(id))
                .map(livro -> {
                    ResultadoPesquisaLivroDTO dto = mapper.toDto(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
