package com.springdemo.adapters.web;

import com.springdemo.adapters.web.dto.CustomerDto;
import com.springdemo.application.exception.CustomerException;
import com.springdemo.domain.Customer;
import com.springdemo.application.port.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController

public class CustomerResource {
    private final CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService adService) {
        this.customerService = adService;
    }

    @GetMapping(value = "/{id}/balance")
    public CustomerDto get(@PathVariable String customerId) throws CustomerException {

        return  new CustomerDto(customerService.getCustomer(customerId).getBalance());
    }

    @PostMapping(value = "{id}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto createCustomer(@Valid @RequestBody CustomerDto customerDto,@PathVariable String customerId)
            throws CustomerException {
        Customer customer = customerService.upsert(customerDto.getAmount(), customerId);
        return new CustomerDto(customerService.getCustomer(customerId).getBalance());
    }



}
