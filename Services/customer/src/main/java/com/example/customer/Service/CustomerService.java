package com.example.customer.Service;


import com.example.customer.CustomerRepository.CustomerRepository;
import com.example.customer.Dto.CustomerRequest;
import com.example.customer.Dto.CustomerResponse;
import com.example.customer.Entity.Customer;
import com.example.customer.Exception.CustomerNotFoundException;
import com.example.customer.Mapper.CustomerMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    public CustomerRepository customerRepository;
    public CustomerMapper customerMapper;
    @Autowired
    public CustomerService(CustomerRepository customerRepository,CustomerMapper customerMapper){
        this.customerRepository=customerRepository;
        this.customerMapper=customerMapper;
    }
    public String createCustomer(@Valid CustomerRequest customerRequest) {
        var createdCustomer=customerRepository.save(customerMapper.toCustomer(customerRequest));
        return createdCustomer.getId();
    }


    public void updateCustomer(CustomerRequest customerRequest) {
        var customer=customerRepository.findById(customerRequest.id()).orElseThrow(
                ()-> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID: %s", customerRequest.id())
                ));
        mergeCustomer(customer, customerRequest);
        customerRepository.save(customerMapper.toCustomer(customerRequest));
    }
    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> getCustomerDetails() {
        return customerRepository.findAll().stream().map(customer->
                customerMapper.fromCustomer(customer)).
                collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse getCustomerById(String customerId) {
        return customerRepository.findById(customerId).map(customerMapper::fromCustomer).orElseThrow(
                ()->new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID: %s", customerId)
                )
        );
    }

    public Boolean deleteCustomerId(String customerId) {
        customerRepository.deleteById(customerId);
        return customerRepository.findById(customerId).isEmpty();

    }
}
