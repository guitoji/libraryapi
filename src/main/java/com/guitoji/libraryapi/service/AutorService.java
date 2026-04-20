package com.guitoji.libraryapi.service;

import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.repository.AutorRepository;
import com.guitoji.libraryapi.validator.AutorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;

    private final AutorValidator validator;

    public AutorService(AutorRepository Repository, AutorValidator validator) {
        this.repository = Repository;
        this.validator = validator;
    }

    public Autor salvar(Autor autor) {
        validator.validar(autor);
        return repository.save(autor);
    }

    public void atualizar(Autor autor) {
        if (autor.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor já esteja salvo na base.");
        }
        validator.validar(autor);
        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletarAutor(Autor autor) {
        repository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade) {
        //Irei refatorar este algoritmo após algumas aulas, pois não é um metodo elegante
        if (nome != null && nacionalidade != null) {
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if (nome != null) {
            return repository.findByNome(nome);
        }

        if (nacionalidade != null) {
            return repository.findByNacionalidade(nacionalidade);
        }

        return repository.findAll();
    }
}

