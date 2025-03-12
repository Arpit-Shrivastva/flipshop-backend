package com.example.FlipShop.Services;

import com.example.FlipShop.Models.Products;
import com.example.FlipShop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Products> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Products addProducts(Products products) {
        return productRepository.save(products);
    }

    
    @Override
    public Products updateProduct(int productId, Products products) {
        Optional<Products> existingProduct = productRepository.findById(productId);

        if (existingProduct.isPresent()) {
            Products product = existingProduct.get();
            product.setProductName(product.getProductName());
            product.setProductUrl(product.getProductUrl());
            product.setProductDesc(product.getProductDesc());
            product.setProductPrice(product.getProductPrice());
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product with ID " + productId + " not found.");
        }
    }

    @Override
    public boolean deleteProduct(int productId) {
        Optional<Products> product = productRepository.findById(productId);

        if (product.isPresent()) {
            productRepository.delete(product.get());
            return true;
        } else {
            throw new RuntimeException("Product with ID " + productId + " not found.");
        }
    }
}
