package com.springdemo.application.service;

import com.springdemo.adapters.repository.jpa.CustomerJPARepository;
import com.springdemo.application.port.CustomerRepository;
import com.springdemo.application.exception.CustomerException;
import com.springdemo.application.port.CustomerService;
import com.springdemo.domain.Customer;


import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerJPARepository customerJPARepositoryPort) {
        this.customerRepository = customerJPARepositoryPort;
    }

    @Override
    public Customer getCustomer(String customerId) throws CustomerException {
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerException("customer could not be found"));

    }

    @Override
    public Customer upsert(Double amount, String customerId) throws CustomerException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer c;
        if (optionalCustomer.isPresent()) {
            c = optionalCustomer.get();
            c.setBalance(c.getBalance() + amount);
        } else {
            c = new Customer(customerId, amount);
        }
        customerRepository.save(c);
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerException("customer could not be found"));
    }
}
