package com.guitoji.libraryapi.validator;

import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

@Component
public class AutorValidador {

    private AutorRepository repository;

    public AutorValidador(AutorRepository repository) {
        this.repository = repository;
    }

    public void validar(Autor autor) {

    }
}
