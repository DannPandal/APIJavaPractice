package com.example.semana8.repository;


import com.example.semana8.model.Author;
import com.example.semana8.model.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Long> {


    @Query(value = "SELECT * FROM editor WHERE status <> 0", nativeQuery = true)
    Optional<List<Editor>> findAllWithStatusActive();

    Optional<Editor> findByIdAndStatusNot(Long id, Integer status);

    boolean existsByIdAndStatusNot(Long id, Integer status);
}
