package com.ecommerce.customer.api.service.impl;


import com.ecommerce.customer.api.dto.ShippingAddressDTO;
import com.ecommerce.customer.api.entity.CustomerEntity;
import com.ecommerce.customer.api.entity.ShippingAddressEntity;
import com.ecommerce.customer.api.exception.ResourceNotFoundException;
import com.ecommerce.customer.api.repository.CustomerRepository;
import com.ecommerce.customer.api.repository.ShippingAddressRepository;
import com.ecommerce.customer.api.service.ShippingAddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public ShippingAddressDTO saveAddress(Long customerId, ShippingAddressDTO addressDTO) {
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        ShippingAddressEntity entity = modelMapper.map(addressDTO, ShippingAddressEntity.class);
        entity.setCustomer(customer);

        ShippingAddressEntity saved = shippingAddressRepository.save(entity);
        return modelMapper.map(saved, ShippingAddressDTO.class);
    }

    @Override
    public ShippingAddressDTO getAddressById(Long addressId) {
        ShippingAddressEntity entity = shippingAddressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + addressId));
        return modelMapper.map(entity, ShippingAddressDTO.class);
    }

    @Override
    public ShippingAddressDTO updateAddress(Long addressId, ShippingAddressDTO addressDTO) {
        ShippingAddressEntity entity = shippingAddressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + addressId));

        entity.setHouseNum(addressDTO.getHouseNum());
        entity.setStreet(addressDTO.getStreet());
        entity.setCity(addressDTO.getCity());
        entity.setState(addressDTO.getState());
        entity.setZipcode(addressDTO.getZipcode());
        entity.setCountry(addressDTO.getCountry());

        ShippingAddressEntity updated = shippingAddressRepository.save(entity);
        return modelMapper.map(updated, ShippingAddressDTO.class);
    }

    @Override
    public List<ShippingAddressDTO> getAddressesByCustomerId(Long customerId) {
        // Validate customer existence first
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        // Fetch addresses
        List<ShippingAddressEntity> addresses = shippingAddressRepository.findByCustomerId(customerId);

        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException("No addresses found for customer with id: " + customerId);
        }

        return addresses.stream()
                .map(addr -> modelMapper.map(addr, ShippingAddressDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteAddress(Long addressId) {
        ShippingAddressEntity entity = shippingAddressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + addressId));
        shippingAddressRepository.delete(entity);
    }

}

