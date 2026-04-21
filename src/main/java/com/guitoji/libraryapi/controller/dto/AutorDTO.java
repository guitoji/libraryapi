package com.guitoji.libraryapi.controller.dto;

import com.guitoji.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.mapstruct.MapperConfig;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Nome é obrigatório")
        @Size(min= 2,max = 100, message = "Campo fora do tamanho padrão")
        String nome,
        @NotNull(message = "Data de nascimento é obrigatória")
        @Past(message = "Não é permitido uma data futura")
        LocalDate dataNascimento,
        @NotBlank(message = "Nacionalidade é obrigatória")
        @Size(min = 2, max = 50, message = "Campo fora do tamanho padrão")
        String nacionalidade
) {
}
