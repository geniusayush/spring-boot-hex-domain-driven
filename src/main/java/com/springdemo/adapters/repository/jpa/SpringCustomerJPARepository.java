package com.springdemo.adapters.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface SpringCustomerJPARepository extends JpaRepository<CustomerEntity, String> {


    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Override
    CustomerEntity getById(String id);
}
