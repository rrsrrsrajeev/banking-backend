package com.abhay.banking_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhay.banking_backend.dto.CustomerRequest;
import com.abhay.banking_backend.dto.CustomerResponse;
import com.abhay.banking_backend.entity.Customer;
import com.abhay.banking_backend.exception.CustomerNotFoundException;
import com.abhay.banking_backend.exception.DuplicateEmailException;
import com.abhay.banking_backend.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CustomerRequest request) {
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateEmailException(
                    "Email already registered");
        }
        Customer customer = new Customer();

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        Customer savedCustomer = customerRepository.save(customer);

        return mapToResponse(savedCustomer);
    }

    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CustomerResponse getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        return mapToResponse(customer);
    }

    public CustomerResponse updateCustomer(
            Long id,
            CustomerRequest request) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        existingCustomer.setName(request.getName());
        existingCustomer.setEmail(request.getEmail());
        existingCustomer.setPhone(request.getPhone());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return mapToResponse(updatedCustomer);
    }

    public void deleteCustomer(Long id) {

        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found");
        }

        customerRepository.deleteById(id);
    }

    private CustomerResponse mapToResponse(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedAt());
    }
}