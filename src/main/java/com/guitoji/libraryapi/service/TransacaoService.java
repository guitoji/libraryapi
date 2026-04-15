package com.guitoji.libraryapi.service;

import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.model.GeneroLivro;
import com.guitoji.libraryapi.model.Livro;
import com.guitoji.libraryapi.repository.AutorRepository;
import com.guitoji.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroRepository.findById(UUID.fromString("8ee8dda8-b8c4-4666-bb6c-edd9c1909d96"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024, 6, 1));

//      livroRepository.save(livro); não é necessário utilizar o save, pois em transação ele estará em estado managed
    }

    @Transactional
    public void executar() {

        // salva o autor
        Autor autor = new Autor();
        autor.setNome("Teste Francisco");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1988, 11, 22));

        autorRepository.save(autor); // utilizar saveAndFlush somente se necessário

        // salva o livro
        Livro livro = new Livro();
        livro.setIsbn("9236455514977");
        livro.setPreco(BigDecimal.valueOf(20));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Teste livro do Francisco");
        livro.setDataPublicacao(LocalDate.of(2022, 7, 11));

        livro.setAutor(autor);
        livroRepository.save(livro); // utilizar saveAndFlush somente se necessário

        // forcando lancamento de exceção
        if (autor.getNome().equals("Teste Francisco")) {
            throw new RuntimeException("Rollback!");
        }
    }
}
