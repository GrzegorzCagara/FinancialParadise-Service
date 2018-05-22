package com.sda.spring.controller;

import com.sda.spring.entity.Customer;
import com.sda.spring.service.AccountServiceImpl;
import com.sda.spring.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final AccountServiceImpl accountService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService, AccountServiceImpl accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping("/list")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        accountService.save(customer.getAccount());
        customerService.save(customer);
        return new ResponseEntity<>("New customer has been added", HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        accountService.save(customer.getAccount());
        customerService.save(customer);
        return new ResponseEntity<>("Customer has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/send" + "{email}")
    public Customer getCustomerByEmail(@PathVariable("email") String email) {
        return customerService.getCustomerByEmail(email);
    }
}
