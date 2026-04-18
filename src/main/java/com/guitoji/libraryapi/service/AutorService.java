package com.guitoji.libraryapi.service;

import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository Repository) {
        repository = Repository;
    }

    public Autor salvar(Autor autor) {
        return repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }
}

