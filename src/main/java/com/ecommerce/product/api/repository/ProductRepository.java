package com.ecommerce.product.api.repository;



import com.ecommerce.product.api.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Find products by category
    List<ProductEntity> findByCategoryId(Long categoryId);

    // Search products by name (partial match)
    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}

