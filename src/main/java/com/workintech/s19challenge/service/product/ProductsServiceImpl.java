package com.workintech.s19challenge.service.product;

import com.workintech.s19challenge.entity.order.Order;
import com.workintech.s19challenge.entity.product.Products;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.products.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService{

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Products findById(long id) {
        Optional<Products> productsWithId= productsRepository.findById(id);
        if(productsWithId.isPresent()){
            return productsWithId.get();
        }else{
            throw new GlobalException("Failed to find the product related to the given id"+id, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Products saveProducts(Products products) {
        return productsRepository.save(products);
    }

    @Override
    public Products deleteProductsWithId(long id) {
        Products products = findById(id);
        if(products != null ){
            productsRepository.delete(products);
            return products;
        }else{
            throw new GlobalException("Failed to find the order related to the given id"+id, HttpStatus.BAD_REQUEST);
        }
    }
}
