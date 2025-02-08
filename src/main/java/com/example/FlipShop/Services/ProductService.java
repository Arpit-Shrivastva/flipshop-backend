package com.example.FlipShop.Services;

import com.example.FlipShop.Models.Products;

import java.util.List;

public interface ProductService {
    List<Products> getProducts();
    Products addProducts(Products products);
    Products updateProduct(int productId, Products products);
    boolean deleteProduct(int productId);
}
