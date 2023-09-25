package com.example.semana8.repository;

import com.example.semana8.model.Book;
import com.example.semana8.model.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book WHERE status <> 0", nativeQuery = true)
    Optional<List<Book>> findAllWithStatusActive();

    Optional<Book> findByIdAndStatusNot(Long id, Integer status);

    boolean existsByIdAndStatusNot(Long id, Integer status);
}
