package com.ecommerce.customer.api.service;


import com.ecommerce.customer.api.dto.ShippingAddressDTO;

import java.util.List;

public interface ShippingAddressService {

    ShippingAddressDTO saveAddress(Long customerId, ShippingAddressDTO addressDTO);

    ShippingAddressDTO getAddressById(Long addressId);

    ShippingAddressDTO updateAddress(Long addressId, ShippingAddressDTO addressDTO);

    List<ShippingAddressDTO> getAddressesByCustomerId(Long customerId);

    void deleteAddress(Long addressId);
}

