package com.springdemo.application.port;

import com.springdemo.domain.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(String customerId);

    void save(Customer c);
}
