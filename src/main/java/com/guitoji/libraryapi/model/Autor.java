package com.guitoji.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor", schema = "public") // anotado o schema por questão didática
@Getter // Gera getter em tempo de compilação com LOMBOK
@Setter // Gera setter em tempo de compilação com LOMBOK
@ToString // Gera toString
public class Autor {

    // Mapeamento opcional do Column
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

//    @OneToMany(mappedBy = "autor") // Um autor para muitos livros
    @Transient
    private List<Livro> livros;
}
