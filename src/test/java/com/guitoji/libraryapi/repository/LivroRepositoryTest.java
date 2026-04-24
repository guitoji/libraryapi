package com.guitoji.libraryapi.repository;

import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.model.GeneroLivro;
import com.guitoji.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
        livro.setIsbn("9780618275448");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Rei macaco: A queda do Rei");
        livro.setDataPublicacao(LocalDate.of(2020, 7, 29));

        Autor autor = autorRepository
                .findById(UUID.fromString("cd34c396-0a85-4db6-88a9-49f2a5abdbf4"))
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
    public void atualizarAutorDolivroTest() {
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
        UUID id = UUID.fromString("ebe73cfc-0c57-4f5b-88fa-e1fb4e1d3f48");
        Optional<Livro> livroParaDeletar = repository.findById(id);

        if (livroParaDeletar.isPresent()) {
            Livro livro = livroParaDeletar.get();
            repository.delete(livro);
            System.out.println("Livro " + livro.getTitulo() + " deletado!");
        }
    }

    @Test
    @Transactional
    public void buscarLivroTest() {
        UUID id = UUID.fromString("1c8e1a47-0a92-48c0-b4da-c9b13f2cab69");
        Livro livro = repository.findById(id).orElse(null);

        if (livro != null) {
            System.out.println("Livro: ");
            System.out.println(livro.getTitulo());
            System.out.println("Autor: ");
            System.out.println(livro.getAutor().getNome());
        }
    }

    @Test
    public void pesquisaPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("Senhor dos Anéis - A Sociedade do Anel");
        lista.forEach(System.out::println);
    }

    @Test
    public void pesquisaPorIsbnTest() {
        Optional<Livro> livro = repository.findByIsbn("9788501401380");
        livro.ifPresent(System.out::println);
    }


    @Test
    public void pesquisaPorTituloEPrecoTest() {
        List<Livro> lista = repository.findByTituloAndPreco("Senhor dos Anéis - A Sociedade do Anel", BigDecimal.valueOf(100));
        lista.forEach(System.out::println);
    }

    @Test
    public void pesquisaPorDataPublicacaoTest() {
        List<Livro> lista = repository.findByDataPublicacao(LocalDate.of(1954, 7, 29));
        lista.forEach(System.out::println);
    }

    @Test
    public void pesquisaPorTituloOuIsbn() {
        List<Livro> lista = repository.findByTituloOrIsbn("Senhor dos Anéis - A Sociedade do Anel", "9780618260515");
        lista.forEach(System.out::println);
    }

    @Test
    public void listarLivrosComQueryJPQL() {
        var resultado = repository.listarTodosOrdenadorPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarAutoresDosLivros() {
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarTitulosDiferentes() {
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarGenerosDeAutoresBritânicos() {
        var resultado = repository.listarGeneroAutoresBritanicos();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarLivrosDeAutoresAmericanos() {
        var resultado = repository.listarTitulosDeAutoresAmericanos();
        resultado.forEach(System.out::println);
    }

    @Test
    public void pesquisaPorGeneroTest() {
        List<Livro> lista = repository.findByGenero(GeneroLivro.valueOf("Fantasia".toUpperCase()));
        lista.forEach(System.out::println);
    }

    @Test
    public void pesquisaPorGeneroPositionalParamTest() {
        List<Livro> lista = repository.findByGeneroPositionalParameters(GeneroLivro.valueOf("Fantasia".toUpperCase()));
        lista.forEach(System.out::println);
    }

    @Test
    public void deletePorTituloTest() {
        repository.deleteByTitulo("Trono de Vidro: Coroa da Meia Noite - Vol 2");
    }
}