package com.springdemo.adapters.repository;

import com.springdemo.adapters.repository.jpa.CustomerEntity;
import com.springdemo.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerPersistenceMapper {

    CustomerPersistenceMapper INSTANCE = Mappers.getMapper( CustomerPersistenceMapper.class );
    CustomerEntity toCustomerEntity(Customer customer);

    Customer toCustomer(CustomerEntity customerEntity);
}
