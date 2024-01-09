package com.workintech.s19challenge.service.product;

import com.workintech.s19challenge.entity.product.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Category findById(long id);
    Category saveCategory(Category category);
    Category deleteCategory(long id);
}
