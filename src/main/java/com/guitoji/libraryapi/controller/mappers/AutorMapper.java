package com.guitoji.libraryapi.controller.mappers;

import com.guitoji.libraryapi.controller.dto.AutorDTO;
import com.guitoji.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
