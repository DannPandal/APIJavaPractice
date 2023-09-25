package com.example.semana8.repository;

import com.example.semana8.model.Category;
import com.example.semana8.model.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM category WHERE status <> 0", nativeQuery = true)
    Optional<List<Category>> findAllWithStatusActive();

    Optional<Category> findByIdAndStatusNot(Long id, Integer status);

    boolean existsByIdAndStatusNot(Long id, Integer status);
}
