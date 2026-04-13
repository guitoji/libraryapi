package com.guitoji.libraryapi.Repository;

import com.guitoji.libraryapi.model.Autor;
import com.guitoji.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query Method
    //select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor);
}
