package com.guitoji.libraryapi.repository;

import com.guitoji.libraryapi.Repository.AutorRepository;
import com.guitoji.libraryapi.Repository.LivroRepository;
import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.model.GeneroLivro;
import com.guitoji.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("9780618260515");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Senhor dos Anéis: A Sociedade do Anel");
        livro.setDataPublicacao(LocalDate.of(1954, 7, 29));

        Autor autor = autorRepository
                .findById(UUID.fromString("6a1a9fd8-2800-4b5d-b0c2-1da32f694920"))
                .orElse(null);

        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    public void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("9786555114744");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Crônicas de Nárnia: O Leão, a Feiticeira e o Guarda-Roupa");
        livro.setDataPublicacao(LocalDate.of(1950, 10, 16));

        Autor autor = new Autor();
        autor.setNome("Clive Staples Lewis");
        autor.setNacionalidade("Britânico");
        autor.setDataNascimento(LocalDate.of(1898, 11, 22));

        autorRepository.save(autor);

        livro.setAutor(autor);
        repository.save(livro);
    }
    
    @Test
    public void contagemDeLivrosTest() {
        System.out.println("Contagem de Livros: " + repository.count());
    }

    @Test
    public void aualizarAutorDolivroTest() {
        UUID id = UUID.fromString("b298adf6-7878-4fba-9751-16321167d136");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        if (livroParaAtualizar != null) {
            UUID idAutor = UUID.fromString("744ae545-4211-441c-a008-91e0a80ceccb");
            Autor autor = autorRepository.findById(idAutor).orElse(null);

            if (autor != null) {
                livroParaAtualizar.setAutor(autor);

                repository.save(livroParaAtualizar);
            }
        }
    }

    @Test
    public void deletarPorIdTest() {
        UUID id = UUID.fromString("b298adf6-7878-4fba-9751-16321167d136");
        Optional<Livro> livroParaDeletar = repository.findById(id);

        if (livroParaDeletar.isPresent()) {
            repository.deleteById(id);
        }
    }

    @Test
    public void deletarTest() {
        UUID id = UUID.fromString("b298adf6-7878-4fba-9751-16321167d136");
        Optional<Livro> livroParaDeletar = repository.findById(id);

        if (livroParaDeletar.isPresent()) {
            Livro livro = livroParaDeletar.get();
            repository.delete(livro);
            System.out.println("Livro " + livro.getTitulo() + " deletado!");
        }
    }
}