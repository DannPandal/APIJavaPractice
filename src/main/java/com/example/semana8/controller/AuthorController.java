package com.example.semana8.controller;

import com.example.semana8.model.Author;
import com.example.semana8.service.implement.AuthorServiceImplement;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author/")
public class AuthorController {

    private final AuthorServiceImplement authorServiceImplement;

    public AuthorController(AuthorServiceImplement authorServiceImplement) {
        this.authorServiceImplement = authorServiceImplement;
    }

    @GetMapping()
    public List<Author> getListAllAuthor(){
        return authorServiceImplement.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        Author authorSearch = authorServiceImplement.getAuthorById(id);
        return ResponseEntity.ok(authorSearch);
    }

    @GetMapping("/searchByName/param/{name}")
    public ResponseEntity<Author> getAuthorByNameFisrt(@PathVariable String name){
        Author authorSearch = authorServiceImplement.getAuthorByName(name);
        return ResponseEntity.ok(authorSearch);
    }
    @PostMapping("/searchByName/body")
    public ResponseEntity<Author> getAuthorByNameSecond(@RequestBody Author request) {
        Author authorSearch = authorServiceImplement.getAuthorByName(request.getName());
        return ResponseEntity.ok(authorSearch);
    }

    @PostMapping()
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        Author authorCreated = authorServiceImplement.saveAuthor(author);
        return new ResponseEntity<>(authorCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author ){
        Author authorUpdated = authorServiceImplement.updateAuthor(id, author);
        return ResponseEntity.ok(authorUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        String message = authorServiceImplement.deleteAuthor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
