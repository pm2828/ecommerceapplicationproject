package com.ecommerce.customer.api.controller;

import com.ecommerce.customer.api.dto.*;

import com.ecommerce.customer.api.service.CustomerService;
import com.ecommerce.customer.api.service.ShippingAddressService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ShippingAddressService shippingAddressService;

    // ---------------- AUTH APIs ----------------

    @Operation(summary = "Register a new customer")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CustomerDTO>> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO saved = customerService.registerCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<CustomerDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Customer registered successfully. Check your email for temporary password.")
                        .data(saved) // ✅ send DTO in response
                        .build()
        );
    }


    @Operation(summary = "Login customer")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<CustomerLoginResponse>> login(@RequestBody CustomerLoginRequest customerLoginRequest) {
        CustomerLoginResponse customerLoginResponse = customerService.login(customerLoginRequest.getEmail(), customerLoginRequest.getPwd());
        return ResponseEntity.ok(ApiResponse.<CustomerLoginResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Login successful")
                .data(customerLoginResponse)
                .build());
    }

    // ---------------- CUSTOMER APIs ----------------

    @Operation(summary = "Get customer by ID")
    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse<CustomerDTO>> getCustomerById(@PathVariable Long customerId) {
        CustomerDTO customer = customerService.getCustomerByID(customerId);
        return ResponseEntity.ok(ApiResponse.<CustomerDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Customer retrieved successfully")
                .data(customer)
                .build());
    }

    @Operation(summary = "Update customer")
    @PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse<CustomerDTO>> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updated = customerService.updateCustomer(customerId, customerDTO);
        return ResponseEntity.ok(ApiResponse.<CustomerDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Customer updated successfully")
                .data(updated)
                .build());
    }

    @Operation(summary = "Delete customer")
    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse<String>> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Customer deleted successfully")
                .data("Deleted customer ID: " + customerId)
                .build());
    }

    // ---------------- SHIPPING ADDRESS APIs ----------------

    @Operation(summary = "Save new shipping address for customer")
    @PostMapping("/{customerId}/addresses")
    public ResponseEntity<ApiResponse<ShippingAddressDTO>> saveAddress(
            @PathVariable Long customerId,
            @RequestBody ShippingAddressDTO dto) {
        ShippingAddressDTO saved = shippingAddressService.saveAddress(customerId, dto);
        return ResponseEntity.ok(ApiResponse.<ShippingAddressDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Address saved successfully")
                .data(saved)
                .build());
    }

    @Operation(summary = "Get shipping address by ID")
    @GetMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<ApiResponse<ShippingAddressDTO>> getAddressById(
            @PathVariable Long addressId) {
        ShippingAddressDTO address = shippingAddressService.getAddressById(addressId);
        return ResponseEntity.ok(ApiResponse.<ShippingAddressDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Address retrieved successfully")
                .data(address)
                .build());
    }

    @Operation(summary = "Get all addresses of a customer")
    @GetMapping("/{customerId}/addresses")
    public ResponseEntity<ApiResponse<List<ShippingAddressDTO>>> getAddresses(@PathVariable Long customerId) {
        List<ShippingAddressDTO> addresses = shippingAddressService.getAddressesByCustomerId(customerId);
        return ResponseEntity.ok(ApiResponse.<List<ShippingAddressDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Addresses retrieved successfully")
                .data(addresses)
                .build());
    }

    @Operation(summary = "Update shipping address")
    @PutMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<ApiResponse<ShippingAddressDTO>> updateAddress(
            @PathVariable Long addressId,
            @RequestBody ShippingAddressDTO addressDTO) {
        ShippingAddressDTO updated = shippingAddressService.updateAddress(addressId, addressDTO);
        return ResponseEntity.ok(ApiResponse.<ShippingAddressDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Address updated successfully")
                .data(updated)
                .build());
    }

    @Operation(summary = "Delete shipping address by ID")
    @DeleteMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<ApiResponse<String>> deleteAddress(@PathVariable Long addressId) {
        shippingAddressService.deleteAddress(addressId);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Address deleted successfully")
                .data("Deleted ID: " + addressId)
                .build());
    }
    @PutMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword( @RequestParam String email, @RequestParam String password) {

        customerService.resetPassword(email, password);

      return ResponseEntity.ok(ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Password Reset Successfully successfully")
                .data("Deleted ID: " + email)
                .build());
    }
}
