package com.workintech.s19challenge.service.product;

import com.workintech.s19challenge.entity.product.Products;

import java.util.List;

public interface ProductsService {
    List<Products> findAll();
    Products findById(long id);

    Products saveProducts(Products products);
    Products deleteProductsWithId(long id);
}
