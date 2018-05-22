package com.sda.spring.service;

import com.sda.spring.entity.Customer;

import java.util.List;

public interface CustomerService {

    void save(Customer customer);

    Customer getCustomerById(Integer id);

    List<Customer> getAllCustomers();

    Customer getCustomerByAccountNumber(String accountNumber);

    Customer getCustomerByEmail(String email);

    void deleteCustomerById(Integer id);
}
