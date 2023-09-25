package com.example.semana8.service.implement;

import com.example.semana8.exception.ExceptionNotFoundEntity;
import com.example.semana8.model.Author;
import com.example.semana8.repository.AuthorRepository;
import com.example.semana8.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImplement implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImplement(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public List<Author> getAllAuthors() {
        Optional<List<Author>> authors = authorRepository.findAllWithStatusActive();
        if (authors.isPresent()) {
            return authors.get();
        }
        throw new ExceptionNotFoundEntity("Author not found");
//        return authorRepository.findAll()
//                .stream()
//                .filter(author -> author.getStatus() != 0)
//                .collect(Collectors.toList());
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Author getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findByIdAndStatusNot(id, 0);
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new ExceptionNotFoundEntity("Author not found");
        }
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Author getAuthorByName(String name) {
        Optional<Author> author = authorRepository.findByNameAndStatusNot(name, 0);
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new ExceptionNotFoundEntity("Author not found with name: " + name);
        }
    }

    @Override
    public Author saveAuthor(Author author) {
        // validate if status is null, set 1 by default
        author.setStatus(author.getStatus() == null ? 1 : author.getStatus());
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        if (existsAuthorById(id)) {
            Author existingAuthor = getAuthorById(id);
            existingAuthor.setId(id);
            existingAuthor.setName(author.getName());
            // validate if status is null, set 1 by default
            existingAuthor.setStatus(author.getStatus() == null ? 1 : author.getStatus());
            return authorRepository.save(existingAuthor);
        }
        return null;
    }

    @Override
    public String deleteAuthor(Long id) {
        if (existsAuthorById(id)) {
            Author existingAuthor = getAuthorById(id);
            existingAuthor.setStatus(0);
            authorRepository.save(existingAuthor);
            return "Author removed !! ";
        }
        return "Author not found !! ";
    }

    // New method to check if an author exists by id and status != 0
    @Override
    public Boolean existsAuthorById(Long id) {
        return authorRepository.existsByIdAndStatusNot(id, 0);
    }

}
