package com.guitoji.libraryapi.controller.dto;

import com.guitoji.libraryapi.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(
        @ISBN
        @NotBlank(message = "ISBN é obrigatório")
        String isbn,
        @NotBlank(message = "Titulo é obrigatório")
        String titulo,
        @NotNull(message = "Data de Publicação é obrigatória")
        @Past(message = "Não são permitidas datas futuras")
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        @NotNull(message = "Id do autor é obrigatório")
        UUID idAutor
) {
}
