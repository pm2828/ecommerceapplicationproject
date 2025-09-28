package com.ecommerce.customer.api.repository;


import com.ecommerce.customer.api.entity.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddressEntity, Long> {
    List<ShippingAddressEntity> findByCustomerId(Long customerId);
}

