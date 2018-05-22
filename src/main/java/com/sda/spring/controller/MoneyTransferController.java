package com.sda.spring.controller;

import com.sda.spring.dto.CustomerWithTransferReceiver;
import com.sda.spring.entity.Customer;
import com.sda.spring.service.CustomerServiceImpl;
import com.sda.spring.service.MoneyTransferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transfer")
public class MoneyTransferController {

    private final MoneyTransferServiceImpl moneyTransferService;
    private final CustomerServiceImpl customerService;

    @Autowired
    public MoneyTransferController(MoneyTransferServiceImpl moneyTransferService, CustomerServiceImpl customerService) {
        this.moneyTransferService = moneyTransferService;
        this.customerService = customerService;
    }

    @PostMapping("")
    public ResponseEntity<?> sendMoney(@RequestBody CustomerWithTransferReceiver customerWithTransferReceiver) {
        Customer customerFrom = customerWithTransferReceiver.getCustomer();
        Customer customerTo = customerService.getCustomerByAccountNumber(customerWithTransferReceiver.getTransferReceiver().getBankAccountNumber());
        moneyTransferService.transferMoney(customerFrom, customerTo, customerWithTransferReceiver.getTransferReceiver().getAmount(),
                customerWithTransferReceiver.getTransferReceiver().getTitle());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
