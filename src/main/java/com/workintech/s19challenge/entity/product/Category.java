package com.workintech.s19challenge.entity.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "category",schema = "fsweb2")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<Products> productList;

    public void add(Products products){
        if(productList == null){
            productList = new ArrayList<>();
        }else{
            productList.add(products);
        }
    }
}
