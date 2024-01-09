package com.workintech.s19challenge.repository.products;

import com.workintech.s19challenge.entity.product.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products,Long> {
}
