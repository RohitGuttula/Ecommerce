package com.example.product.Controller;

import com.example.product.Dto.ProductRequest;
import com.example.product.Dto.ProductResponse;
import com.example.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    public ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @PostMapping("/createProduct")
    public ResponseEntity<Integer> createProduct(@RequestBody ProductRequest productRequest){
           return ResponseEntity.ok(productService.createProduct(productRequest));
    }
    @GetMapping("/fetchAllProducts")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
          return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable ("product-id") Integer productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
