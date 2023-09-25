package com.example.semana8.service.implement;

import com.example.semana8.exception.ExceptionNotFoundEntity;
import com.example.semana8.model.Author;
import com.example.semana8.model.Category;
import com.example.semana8.repository.CategoryRepository;
import com.example.semana8.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplement implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public List<Category> getAllCategories() {
        Optional<List<Category>> categories = categoryRepository.findAllWithStatusActive();
        if (categories.isPresent()){
            return categories.get();
        }
        throw new ExceptionNotFoundEntity("Categories not found");
//        return categoryRepository.findAll();
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findByIdAndStatusNot(id,0);
        if (category.isPresent()){
            return category.get();
        }
        else{
            throw new ExceptionNotFoundEntity("Category not found");
        }
    }

    @Override
    public Category saveCategory(Category category) {
        category.setStatus(category.getStatus() == null ? 1 : category.getStatus());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        if (existsCategoryById(id)){
            Category existingCategory = getCategoryById(id);
            existingCategory.setId(id);
            existingCategory.setName(category.getName());
            existingCategory.setStatus(category.getStatus() == null ? 1 : category.getStatus());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    @Override
    public String deleteCategory(Long id) {
        if (existsCategoryById(id)){
            Category category = getCategoryById(id);
            category.setStatus(0);
            categoryRepository.save(category);
            return "Category removed !!";
        }
        return "Category not found !!";
    }

    @Override
    public Boolean existsCategoryById(Long id) {
        return categoryRepository.existsByIdAndStatusNot(id, 0);
    }
}
