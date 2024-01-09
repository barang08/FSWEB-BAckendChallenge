package com.workintech.s19challenge.service.product;

import com.workintech.s19challenge.entity.product.Category;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.products.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(long id) {
        Optional<Category> findCategory = categoryRepository.findById(id);
        if(findCategory.isPresent()){
            return findCategory.get();
        }else{
            throw new GlobalException("No category related to the given id found"+id, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategory(long id) {
       Optional<Category> deleteCategoryWithId = categoryRepository.findById(id);
       if(deleteCategoryWithId.isPresent()){
           Category category = deleteCategoryWithId.get();
           categoryRepository.delete(category);
           return category;
       }else{
           throw new GlobalException("No category related to the given id found"+id, HttpStatus.BAD_REQUEST);
       }
    }
}
