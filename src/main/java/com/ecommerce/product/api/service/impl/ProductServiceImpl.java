package com.ecommerce.product.api.service.impl;

import com.ecommerce.product.api.dto.ProductDTO;
import com.ecommerce.product.api.dto.ProductCategoryDTO;
import com.ecommerce.product.api.entity.ProductEntity;
import com.ecommerce.product.api.exception.CategoryNotFoundException;
import com.ecommerce.product.api.exception.ProductNotFoundException;
import com.ecommerce.product.api.repository.ProductCategoryRepository;
import com.ecommerce.product.api.repository.ProductRepository;
import com.ecommerce.product.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;
    private final ModelMapper modelMapper;




    @Override
    public List<ProductCategoryDTO> getAllCategories() {
        List<ProductCategoryDTO> productCategoryDTOS= categoryRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, ProductCategoryDTO.class))
                .toList();

        if(productCategoryDTOS.isEmpty()) {
            throw new RuntimeException("No Categoy is there");
        }
        return productCategoryDTOS;

    }

    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {

        List<ProductDTO> productDTOS=productRepository.findByCategoryId(categoryId)
                .stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .toList();

        if(productDTOS.isEmpty()){
            throw  new ProductNotFoundException("No Products found with this Category  " + categoryId);
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> searchProductsByName(String name) {


        List<ProductDTO> products = productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .toList();

        if(products.isEmpty()){
            throw  new ProductNotFoundException("No Products found matching " + name);
        }

        return products;

    }

    @Override
    public ProductDTO getProductById(Long productId) {
       ProductDTO productDTO= productRepository.findById(productId)
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .orElse(null); // return null if not found

        if(productDTO == null){

            throw new ProductNotFoundException("No Product found with this ID"+ productId);

        }

        return productDTO;

    }
}
