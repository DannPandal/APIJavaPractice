package com.example.semana8.service.implement;

import com.example.semana8.exception.ExceptionNotFoundEntity;
import com.example.semana8.model.Book;
import com.example.semana8.repository.BookRepository;
import com.example.semana8.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplement implements BookService {

    private  final BookRepository bookRepository;

    public BookServiceImplement(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public List<Book> getAllBooks() {
        Optional<List<Book>> books = bookRepository.findAllWithStatusActive();
        if (books.isPresent()){
            return books.get();
        }
        throw new ExceptionNotFoundEntity("Books not found");
//        return bookRepository.findAll();
//        throw new RuntimeException("Books not found");
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findByIdAndStatusNot(id,0);
        if (book.isPresent()){
            return book.get();
        }
        else {
            throw new ExceptionNotFoundEntity("Book not found");
        }
    }

    @Override
    public Book saveBook(Book book) {
        book.setStatus(book.getStatus() == null ? 1 : book.getStatus());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        if (existsBookById(id)){
            Book existingBook = getBookById(id);
            existingBook.setId(id);
            existingBook.setTitle(book.getTitle());
            existingBook.setStatus(book.getStatus() == null ? 1 : book.getStatus());

            existingBook.setEditor(book.getEditor());
            existingBook.setAuthor(book.getAuthor());

            return bookRepository.save(existingBook);
        }
        return null;
    }

    @Override
    public String deleteBook(Long id) {
        if (existsBookById(id)){
            Book existingBook = getBookById(id);
            existingBook.setStatus(0);
            bookRepository.save(existingBook);
            return "Book removed !! ";
        }
        return "Book not found !! ";
    }

    @Override
    public Boolean existsBookById(Long id) {
        return bookRepository.existsByIdAndStatusNot(id, 0);
    }
}
