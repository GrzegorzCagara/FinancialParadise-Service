package com.sda.spring.repository;

import com.sda.spring.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Transactional
    void deleteCustomerById(Integer id);
    Customer findCustomerById(Integer id);
    Customer findCustomerByEmail(String email);
    Customer findCustomerByAccountBankAccountNumber(String accountNumber);
}
