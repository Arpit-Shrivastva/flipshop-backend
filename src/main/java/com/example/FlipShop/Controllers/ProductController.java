package com.example.FlipShop.Controllers;

import com.example.FlipShop.Models.Products;
import com.example.FlipShop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/add")
    public ResponseEntity<Products> addProduct(@RequestBody Products products) {
        Products createdProduct = productService.addProducts(products);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Products> updateProduct(
            @PathVariable int productId,
            @RequestBody Products products
    ) {
        Products updatedProduct = productService.updateProduct(productId, products);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully.");
    }
}
