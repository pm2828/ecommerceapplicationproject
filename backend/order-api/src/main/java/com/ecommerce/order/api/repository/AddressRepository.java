package com.ecommerce.order.api.repository;

import com.ecommerce.order.api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Address save(Address address);
}
