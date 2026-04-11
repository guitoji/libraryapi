package com.guitoji.libraryapi.repository;

import com.guitoji.libraryapi.Repository.AutorRepository;
import com.guitoji.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("David Sheel");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1978, 6, 14));

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
    public void contagemTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("fa679134-7d4c-479b-9018-15691ec02d02");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            repository.deleteById(id);
            System.out.println("Autor de id " + id + " deletado!");
        }
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("137d2a21-1e9f-4877-ae9a-b005ea984b48");

        Optional<Autor> possivelAutor = repository.findById(id);

        if ((possivelAutor.isPresent())) {
            Autor autor = possivelAutor.get();
            repository.delete(autor);
            System.out.println("Autor " + possivelAutor.get().getNome() + " deletado!");
        }
    }
}
