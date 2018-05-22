package com.sda.spring.service;

import com.sda.spring.entity.Customer;

import java.math.BigDecimal;

public interface MoneyTransferService {

    void transferMoney(Customer customerSender, Customer customerReciever, BigDecimal moneyToTransfer, String title);
}
