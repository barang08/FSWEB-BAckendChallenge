package com.workintech.s19challenge.controller;


import com.workintech.s19challenge.dto.CategoryResponse;
import com.workintech.s19challenge.dto.CreditCardResponse;
import com.workintech.s19challenge.dto.ProductsResponse;
import com.workintech.s19challenge.entity.product.Category;
import com.workintech.s19challenge.entity.product.Products;
import com.workintech.s19challenge.entity.user.CreditCard;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.service.product.CategoryService;
import com.workintech.s19challenge.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;


    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable long id){
        Category category = categoryService.findById(id);
        if(category != null){
            return new CategoryResponse(category.getId(), category.getCategoryName(), category.getTitle());
        }else{
            throw new GlobalException("Category is not found with given id: "+id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public CategoryResponse createCategory(@PathVariable long id,@RequestBody Category newCategory) {
        Category savedCategory = categoryService.saveCategory(newCategory);
        return new CategoryResponse(savedCategory.getId(), savedCategory.getCategoryName(), savedCategory.getTitle());
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable long id, @RequestBody Category category){
        Category newCategory= categoryService.findById(id);
        if(newCategory != null){
            newCategory.setCategoryName(category.getCategoryName());
            newCategory.setTitle(category.getTitle());
            categoryService.saveCategory(newCategory);
        }else{
            throw new GlobalException("Category is not found with given id: "+ id, HttpStatus.NOT_FOUND);
        }
        return new CategoryResponse(newCategory.getId(), newCategory.getCategoryName(), newCategory.getTitle());
    }

    @DeleteMapping("/{id}")
    public CategoryResponse delete(@PathVariable long id){
        Category category = categoryService.findById(id);
        categoryService.deleteCategory(id);
        return new CategoryResponse(category.getId(), category.getCategoryName(), category.getTitle());
    }

}
