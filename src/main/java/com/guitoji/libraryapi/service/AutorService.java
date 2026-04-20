package com.guitoji.libraryapi.service;

import com.guitoji.libraryapi.exceptions.OperacaoNaoPermitidaException;
import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.repository.AutorRepository;
import com.guitoji.libraryapi.repository.LivroRepository;
import com.guitoji.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;
    private final AutorValidator validator;

    public Autor salvar(Autor autor) {
        validator.validar(autor);
        return autorRepository.save(autor);
    }

    public void atualizar(Autor autor) {
        if (autor.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor já esteja salvo na base.");
        }
        validator.validar(autor);
        autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return autorRepository.findById(id);
    }

    public void deletarAutor(Autor autor) {
        if (possuiLivro(autor)) {
            throw new OperacaoNaoPermitidaException("Ação não permitida: O Autor possui livros cadastrados!");
        }

        autorRepository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade) {
        //Irei refatorar este algoritmo após algumas aulas, pois não é um metodo elegante
        if (nome != null && nacionalidade != null) {
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if (nome != null) {
            return autorRepository.findByNome(nome);
        }

        if (nacionalidade != null) {
            return autorRepository.findByNacionalidade(nacionalidade);
        }

        return autorRepository.findAll();
    }

    public boolean possuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);
    }
}

