package com.example.semana8.service;

import com.example.semana8.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();

    public Book getBookById(Long id);

    public Book saveBook(Book book);

    public Book updateBook(Long id, Book book);

    public String deleteBook(Long id);

    public Boolean existsBookById(Long id);

}
