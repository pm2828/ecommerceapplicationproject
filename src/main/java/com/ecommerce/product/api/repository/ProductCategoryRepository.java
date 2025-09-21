package com.ecommerce.product.api.repository;

import com.ecommerce.product.api.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository  extends JpaRepository<ProductCategory, Long> {

}
