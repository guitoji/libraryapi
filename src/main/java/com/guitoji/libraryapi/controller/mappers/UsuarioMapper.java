package com.guitoji.libraryapi.controller.mappers;

import com.guitoji.libraryapi.controller.dto.UsuarioDTO;
import com.guitoji.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
