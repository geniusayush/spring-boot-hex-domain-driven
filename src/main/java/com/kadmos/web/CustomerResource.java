package com.kadmos.web;

import com.kadmos.exception.CustomerException;
import com.kadmos.pojo.Customer;
import com.kadmos.service.CustomerService;
import com.kadmos.web.dto.CustomerDto;
import com.kadmos.web.dto.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController

public class CustomerResource {
    private final CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService adService) {
        this.customerService = adService;
    }

    @GetMapping(value = "/balance")
    public CustomerDto get() throws CustomerException {

        return  new CustomerDto(customerService.getCustomer().getBalance());
    }

    @PostMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto createCustomer(@Valid @RequestBody CustomerDto customerDto)
            throws CustomerException {
        Customer customer = customerService.upsert(customerDto.getAmount());
        return new CustomerDto(customerService.getCustomer().getBalance());
    }

}
