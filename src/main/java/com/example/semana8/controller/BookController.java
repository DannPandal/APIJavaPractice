package com.example.semana8.controller;

import com.example.semana8.model.Author;
import com.example.semana8.model.Book;
import com.example.semana8.service.implement.BookServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book/")
public class BookController {

    private final BookServiceImplement bookServiceImplement;

    public BookController(BookServiceImplement bookServiceImplement) {
        this.bookServiceImplement = bookServiceImplement;
    }

    @GetMapping()
    public List<Book> getListAllBooks(){
        return bookServiceImplement.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book bookSearch = bookServiceImplement.getBookById(id);
        return ResponseEntity.ok(bookSearch);
    }

    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book bookCreated = bookServiceImplement.saveBook(book);
        return ResponseEntity.ok(bookCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book ){
        Book bookUpdated = bookServiceImplement.updateBook(id, book);
        return ResponseEntity.ok(bookUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        String message = bookServiceImplement.deleteBook(id);
        return ResponseEntity.ok(message);
    }


}
