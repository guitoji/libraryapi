package com.guitoji.libraryapi.Repository;

import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.model.GeneroLivro;
import com.guitoji.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query Method
    //select * from livro where id_autor = ?
    List<Livro> findByAutor(Autor autor);

    //select * from livro where titulo = ?;
    List<Livro> findByTitulo(String titulo);

    //select * from livro where isbn = ?;
    Livro findByIsbn(String isbn);

    //select * from livro where genero = ?;
    List<Livro> findByGenero(GeneroLivro generoLivro);

    //select * from livro where titulo = ? and preco = ?;
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    //select *from livro where dataPublicacao = ?;
    List<Livro> findByDataPublicacao(LocalDate dataPublicacao);

    //select * from livro where titulo = ? or isbn = ?;
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);
}
