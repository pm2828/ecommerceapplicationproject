package com.ecommerce.product.api.controller;

import com.ecommerce.product.api.dto.ApiResponse;
import com.ecommerce.product.api.dto.ProductDTO;
import com.ecommerce.product.api.dto.ProductCategoryDTO;
import com.ecommerce.product.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Tag(
        name = "Product API",
        description = "APIs to fetch product categories, search products, and get product details"
)
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private  final ModelMapper modelMapper;



    // 1) GET all categories
    @Operation(summary = "Get all product categories")
    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<ProductCategoryDTO>>> getAllCategories() {
        List<ProductCategoryDTO> categories = productService.getAllCategories();
        if (categories == null) categories = Collections.emptyList();

        ApiResponse<List<ProductCategoryDTO>> response =
                new ApiResponse<>(200, "Categories fetched successfully", categories);
        return ResponseEntity.ok(response);
    }

    // 2) GET products by category_id
    @Operation(summary = "Get products by category ID")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductsByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> products = productService.getProductsByCategory(categoryId);
        if (products == null) products = Collections.emptyList();

        ApiResponse<List<ProductDTO>> response =
                new ApiResponse<>(200, "Products fetched successfully", products);
        return ResponseEntity.ok(response);
    }

    // 3) GET products by product_name (search)
    @Operation(summary = "Search products by name")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> searchProducts(@RequestParam String name) {
        List<ProductDTO> products = productService.searchProductsByName(name);
        if (products == null) products = Collections.emptyList();

        ApiResponse<List<ProductDTO>> response =
                new ApiResponse<>(200, "Products search results", products);
        return ResponseEntity.ok(response);
    }

    // 4) GET product by product_id
    @Operation(summary = "Get product details by ID")
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable Long productId) {
        ProductDTO product = productService.getProductById(productId);

        ApiResponse<ProductDTO> response;
        if (product != null) {
            response = new ApiResponse<>(200, "Product fetched successfully", product);
            return ResponseEntity.ok(response);
        } else {
            response = new ApiResponse<>(404, "Product not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
