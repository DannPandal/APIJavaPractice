package com.example.semana8.service;

import com.example.semana8.model.Author;
import com.example.semana8.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAllCategories();

    public Category getCategoryById(Long id);

    public Category saveCategory(Category category);

    public Category updateCategory(Long id, Category category);

    public String deleteCategory(Long id);

    public Boolean existsCategoryById(Long id);
}
