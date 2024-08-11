package com.example.product.Service;

import com.example.product.Dto.ProductRequest;
import com.example.product.Dto.ProductResponse;
import com.example.product.Exception.ProductNotFoundException;
import com.example.product.Mapper.ProductMapper;
import com.example.product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public ProductRepository productRepository;
    public ProductMapper productMapper;
    @Autowired
    public ProductService(ProductRepository productRepository,ProductMapper productMapper){
        this.productRepository=productRepository;
        this.productMapper=productMapper;
    }
    public Integer createProduct(ProductRequest productRequest) {
       var product=productMapper.toProduct(productRequest);
       return productRepository.save(product).getId();
    }

    public List<ProductResponse> getAllProducts() {

        List<ProductResponse> res=productRepository.findAll().stream()
                .map(productResponse->productMapper.toProductResponse(productResponse))
                .collect(Collectors.toList());
        return res;
    }


    public ProductResponse getProductById(Integer productId) {
        return productRepository.findById(productId).map(productMapper::toProductResponse).orElseThrow(
                ()->new ProductNotFoundException("Product not found with ID:: " + productId));

    }
}
