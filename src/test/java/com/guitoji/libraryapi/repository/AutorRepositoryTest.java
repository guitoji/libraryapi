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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("John Ronald Reuel Tolkien");
        autor.setNacionalidade("Britânico");
        autor.setDataNascimento(LocalDate.of(1892, 1, 3));

        var autorSalvo = repository.save(autor);
        System.out.println(autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("137d2a21-1e9f-4877-ae9a-b005ea984b48");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1968, 6, 14));
            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void contagemDeAutoresTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("d9ef40ba-99ca-41c1-9c64-59a597436adf");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            repository.deleteById(id);
            System.out.println("Autor de id " + id + " deletado!");
        }
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("137d2a21-1e9f-4877-ae9a-b005ea984b48");

        Optional<Autor> autorParaDeletar = repository.findById(id);

        if ((autorParaDeletar.isPresent())) {
            Autor autor = autorParaDeletar.get();
            repository.delete(autor);
            System.out.println("Autor " + autor.getNome() + " deletado!");
        }
    }

    @Test
    public void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Sarah J. Maas");
        autor.setNacionalidade("Norte-Americana");
        autor.setDataNascimento(LocalDate.of(1986, 3, 5));

        Livro livro1 = new Livro();
        livro1.setIsbn("9788501401380");
        livro1.setPreco(BigDecimal.valueOf(89.90));
        livro1.setGenero(GeneroLivro.FANTASIA);
        livro1.setTitulo("Trono de Vidro - Vol 1");
        livro1.setDataPublicacao(LocalDate.of(2012, 8, 2));
        livro1.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("9788501060716");
        livro2.setPreco(BigDecimal.valueOf(89.90));
        livro2.setGenero(GeneroLivro.FANTASIA);
        livro2.setTitulo("Trono de Vidro: Coroa da Meia Noite - Vol 2");
        livro2.setDataPublicacao(LocalDate.of(2014, 7, 18));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro1);
        autor.getLivros().add(livro2);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    public void listarLivrosAutorTest() {
        UUID id = UUID.fromString("1159b411-b362-4c1c-b693-6bd345fe2b5c");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}
