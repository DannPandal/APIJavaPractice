package com.example.semana8.repository;

import com.example.semana8.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long> {

    // internamente se esta enviando el nombre para hacer el where

    @Query(value = "SELECT * FROM author WHERE status <> 0", nativeQuery = true)
    Optional<List<Author>> findAllWithStatusActive();

    Optional<Author> findByNameAndStatusNot(String name, Integer status);

    Optional<Author> findByIdAndStatusNot(Long id, Integer status);

    boolean existsByIdAndStatusNot(Long id, Integer status);



//    @Query(value = "SELECT * FROM authors WHERE id = ?1 AND status <> 0", nativeQuery = true)
//    Optional<Author> findByIdAndStatusNot(Long id);
//
//    @Query("SELECT a FROM Author a WHERE a.id = :id AND a.status <> 0")
//    Optional<Author> findByIdAndStatusNot(@Param("id") Long id);

}
