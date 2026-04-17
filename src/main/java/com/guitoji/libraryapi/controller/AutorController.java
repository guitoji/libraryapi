package com.guitoji.libraryapi.controller;

import com.guitoji.libraryapi.controller.dto.AutorDTO;
import com.guitoji.libraryapi.model.Autor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
// http://localhost:8080/autores
public class AutorController {

    @PostMapping
    public ResponseEntity salvar(@RequestBody AutorDTO autor) {
        return new ResponseEntity("Autor salvo com sucesso! " + autor, HttpStatus.CREATED);
    }
}
