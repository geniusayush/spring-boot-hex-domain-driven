package com.springdemo.adapters.repository.jpa;

import com.springdemo.adapters.repository.CustomerPersistenceMapper;
import com.springdemo.application.port.CustomerRepository;
import com.springdemo.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerJPARepository implements CustomerRepository {
    private SpringCustomerJPARepository jpaRepository;

    public CustomerJPARepository(
        SpringCustomerJPARepository jpaRepository
    ) {
        this.jpaRepository = jpaRepository;

    }

    @Override
    public Optional<Customer> findById(String customerId) {
        Optional<CustomerEntity> customerEntity = jpaRepository.findById(customerId);
        if (customerEntity.isEmpty()) {
            return Optional.empty();
        }
        Customer customer = CustomerPersistenceMapper.INSTANCE.toCustomer(customerEntity.get());
        return Optional.of(customer);
    }

    @Override
    public void save(Customer c) {
        CustomerEntity customerEntity = CustomerPersistenceMapper.INSTANCE.toCustomerEntity(c);
        jpaRepository.save(customerEntity);

    }
}
