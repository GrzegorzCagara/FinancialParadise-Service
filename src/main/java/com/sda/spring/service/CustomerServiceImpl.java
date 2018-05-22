package com.sda.spring.service;

import com.sda.spring.entity.Customer;
import com.sda.spring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void save(Customer customer){
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Integer id){
        return customerRepository.findCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(e -> customers.add(e));
        return customers;
    }

    @Override
    public Customer getCustomerByAccountNumber(String accountNumber){
        return customerRepository.findCustomerByAccountBankAccountNumber(accountNumber);
    }

    @Override
    public Customer getCustomerByEmail(String email){
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public void deleteCustomerById(Integer id){
        customerRepository.deleteCustomerById(id);
    }
}
