package com.example.semana8.service;

import com.example.semana8.model.Author;

import java.util.List;

public interface AuthorService {

    public List<Author> getAllAuthors();

    public Author getAuthorById(Long id);

    public Author getAuthorByName(String name);

    public Author saveAuthor(Author author);

    public Author updateAuthor(Long id, Author author);

    public String deleteAuthor(Long id);

    public Boolean existsAuthorById(Long id);

}
