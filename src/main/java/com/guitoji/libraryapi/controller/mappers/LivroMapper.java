package com.guitoji.libraryapi.controller.mappers;

import com.guitoji.libraryapi.controller.dto.CadastroLivroDTO;
import com.guitoji.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.model.Livro;
import com.guitoji.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = AutorMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    AutorMapper autorMapper;

    @Mapping(target = "autor", source = "idAutor")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    protected Autor toAutor(UUID idAutor) {
        return autorRepository.findById(idAutor).orElseThrow(() -> new RuntimeException("Autor não encontrado!"));
    }

    @Mapping(target = "autorDTO", source = "autor")
    public abstract ResultadoPesquisaLivroDTO toDto(Livro livro);
}
