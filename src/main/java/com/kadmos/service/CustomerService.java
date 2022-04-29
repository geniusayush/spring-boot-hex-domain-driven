package com.kadmos.service;

import com.kadmos.exception.CustomerException;
import com.kadmos.pojo.Customer;
import com.kadmos.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Value("${customer.id}")
    private String customerId;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer() throws CustomerException {
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerException("customer could not be found"));

    }

    public Customer upsert(Double amount) throws CustomerException {
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
