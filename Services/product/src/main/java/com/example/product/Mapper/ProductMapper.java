package com.example.product.Mapper;

import com.example.product.Dto.ProductRequest;
import com.example.product.Dto.ProductResponse;
import com.example.product.ProductEntity.Category;
import com.example.product.ProductEntity.Product;

public class ProductMapper {
    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .price(productRequest.price())
                .availableQuantity(productRequest.availableQuantity())
                .description(productRequest.description())
                .category(Category.builder()
                        .id(productRequest.id())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product productResponse) {
        return new ProductResponse(
                productResponse.getId(),
                productResponse.getName(),
                productResponse.getDescription(),
                productResponse.getAvailableQuantity(),
                productResponse.getPrice(),
                productResponse.getCategory().getId(),
                productResponse.getCategory().getName(),
                productResponse.getCategory().getDescription()
        );
    }
}
