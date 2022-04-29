package com.kadmos.repository;

import com.kadmos.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {


    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Override
    Customer getById(String id);
}
