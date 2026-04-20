package com.guitoji.libraryapi.controller.dto;

import com.guitoji.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotNull(message = "Data de nascimento é obrigatória")
        LocalDate dataNascimento,
        @NotBlank(message = "Nacionalidade é obrigatória")
        String nacionalidade
) {

    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
