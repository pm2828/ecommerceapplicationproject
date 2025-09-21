package com.ecommerce.product.api.service;



import com.ecommerce.product.api.dto.ProductDTO;
import com.ecommerce.product.api.dto.ProductDTO;
import com.ecommerce.product.api.dto.ProductCategoryDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

public interface ProductService {

    // 1) Get all product categories
    List<ProductCategoryDTO> getAllCategories();

    // 2) Get products by category ID
    List<ProductDTO> getProductsByCategory(Long categoryId);

    // 3) Search products by name
    List<ProductDTO> searchProductsByName(String name);

    // 4) Get product by product ID
    ProductDTO getProductById(Long productId);
}
