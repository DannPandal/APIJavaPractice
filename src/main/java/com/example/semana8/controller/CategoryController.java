package com.example.semana8.controller;

import com.example.semana8.model.Author;
import com.example.semana8.model.Category;
import com.example.semana8.service.implement.CategoryServiceImplement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category/")
public class CategoryController {

    private final CategoryServiceImplement categoryServiceImplement;

    public CategoryController(CategoryServiceImplement categoryServiceImplement) {
        this.categoryServiceImplement = categoryServiceImplement;
    }

    @GetMapping()
    public List<Category> getListAllAuthor(){
        return categoryServiceImplement.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Category categorySearch = categoryServiceImplement.getCategoryById(id);
        return ResponseEntity.ok(categorySearch);
    }

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category categoryCreated = categoryServiceImplement.saveCategory(category);
        return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category ){
        Category categoryUpdated = categoryServiceImplement.updateCategory(id, category);
        return ResponseEntity.ok(categoryUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        String message = categoryServiceImplement.deleteCategory(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
