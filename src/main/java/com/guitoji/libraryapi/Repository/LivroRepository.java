package com.guitoji.libraryapi.Repository;

import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.model.GeneroLivro;
import com.guitoji.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    //select * from livro where titulo = ? and preco = ?;
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    //select *from livro where dataPublicacao = ?;
    List<Livro> findByDataPublicacao(LocalDate dataPublicacao);

    //select * from livro where titulo = ? or isbn = ?;
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    //select * from livro where data_publicacao between ? and ?;
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    //JPQL - referência as entidades e as propriedades
    //select l.* from livro as l order by l.titulo, l.preco
    @Query("select l from Livro as l order by l.titulo, l.preco")
    List<Livro> listarTodosOrdenadorPorTituloAndPreco();

    //select a.* from livro l join autor a on a.id = l.id_autor
    @Query("select a from Livro l join l.autor a")
    List<Autor> listarAutoresDosLivros();

    //select distinct l.titulo from livro l
    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomesDiferentesLivros();

    @Query("""
        select l.genero from Livro l join l.autor a where a.nacionalidade = 'Britânico' order by l.genero
    """)
    List<String> listarGeneroAutoresBritanicos();

    @Query("""
        select l.titulo from Livro l join l.autor a where a.nacionalidade = 'Americano' order by l.titulo
    """)
    List<String> listarTitulosDeAutoresAmericanos();

    //named parameters -> parametros nomeados
    @Query("select l from Livro l where l.genero = :genero")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro);

    @Query("select l from Livro l where l.genero = ?1")
    List<Livro> findByGeneroPositionalParameters(GeneroLivro generoLivro);
}
