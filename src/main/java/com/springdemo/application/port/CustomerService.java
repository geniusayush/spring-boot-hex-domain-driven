package com.springdemo.application.port;

import com.springdemo.application.exception.CustomerException;
import com.springdemo.domain.Customer;

public interface CustomerService {
    Customer getCustomer(String customerId) throws CustomerException;

    Customer upsert(Double amount, String customerId) throws CustomerException;
}
