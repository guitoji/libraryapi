package com.guitoji.libraryapi.service;

import com.guitoji.libraryapi.model.Livro;
import com.guitoji.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro salvarLivro(Livro livro) {
        return repository.save(livro);
    }
}
