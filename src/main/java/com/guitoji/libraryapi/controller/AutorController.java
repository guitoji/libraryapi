package com.guitoji.libraryapi.controller;

import com.guitoji.libraryapi.controller.dto.AutorDTO;
import com.guitoji.libraryapi.controller.dto.ErroResposta;
import com.guitoji.libraryapi.exceptions.OperacaoNaoPermitidaException;
import com.guitoji.libraryapi.exceptions.RegistroDuplicadoException;
import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/autores")
// http://localhost:8080/autores
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody AutorDTO autor) {
        try {
            Autor autorEntidade = autor.mapearParaAutor();
            service.salvar(autorEntidade);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autorEntidade.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(
                    autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable String id) {
        try {
            UUID idAutor = UUID.fromString(id);
            Optional<Autor> autor = service.obterPorId(idAutor);

            if (autor.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            service.deletarAutor(autor.get());
            return ResponseEntity.noContent().build();
        } catch (OperacaoNaoPermitidaException e) {
            var erroDTO = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

    @GetMapping()
    public ResponseEntity<List<AutorDTO>> filtrar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {

        List<Autor> resultado = service.pesquisa(nome, nacionalidade);
        List<AutorDTO> lista = resultado
                .stream()
                .map(autor -> new AutorDTO(
                        autor.getId(),
                        autor.getNome(),
                        autor.getDataNascimento(),
                        autor.getNacionalidade()))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable String id, @RequestBody AutorDTO dto) {
        try {
            UUID idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = service.obterPorId(idAutor);

            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var autor = autorOptional.get();
            autor.setNome(dto.nome());
            autor.setDataNascimento(dto.dataNascimento());
            autor.setNacionalidade(dto.nacionalidade());

            service.atualizar(autor);

            return ResponseEntity.noContent().build();
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
