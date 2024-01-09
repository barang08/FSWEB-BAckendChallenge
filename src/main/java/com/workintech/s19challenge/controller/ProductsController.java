package com.workintech.s19challenge.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private ProductsService productsService;
    private CategoryService categoryService;

@Autowired
    public ProductsController(ProductsService productsService,CategoryService categoryService) {
        this.productsService = productsService;
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<Products> findAll(){
    return productsService.findAll();
    }

    @GetMapping("/{id}")
    public ProductsResponse getProduct(@PathVariable long id){
    Products product = productsService.findById(id);
        if(product != null){
            return new ProductsResponse(product.getId(), product.getProductName(), product.getProductBrand(), product.getProductDetails());
        }
        throw new GlobalException("Product is not found with given id: "+id, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{categoryId}")
    public ProductsResponse save(@PathVariable long categoryId,@RequestBody Products newProducts){
        Category category = categoryService.findById(categoryId);
        if(category != null){
            newProducts.setCategory(category);
            Products savedProducts = productsService.saveProducts(newProducts);
            return new ProductsResponse(savedProducts.getId(), savedProducts.getProductName(), savedProducts.getProductBrand(), savedProducts.getProductDetails());
        }else{
            throw new GlobalException("Product is not found with given id: "+categoryId, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ProductsResponse update(@PathVariable long id, @RequestBody Products products){
    Products newProduct= productsService.findById(id);
    if(newProduct != null){
        newProduct.setProductName(products.getProductName());
        newProduct.setProductBrand(products.getProductBrand());
        newProduct.setProductDetails(products.getProductDetails());
        productsService.saveProducts(newProduct);
    }else{
        throw new GlobalException("Product is not found with given id: "+ id, HttpStatus.NOT_FOUND);
    }
    return new ProductsResponse(newProduct.getId(), newProduct.getProductName(), newProduct.getProductBrand(), newProduct.getProductDetails());
    }


    @DeleteMapping("/{id}")
    public ProductsResponse delete(@PathVariable long id){
        Products product = productsService.findById(id);
        productsService.deleteProductsWithId(id);
return new ProductsResponse(product.getId(), product.getProductName(), product.getProductBrand(), product.getProductDetails());
}


}
