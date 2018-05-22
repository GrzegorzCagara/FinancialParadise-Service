package com.sda.spring.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class TransferReceiver {

    private String name;
    private String bankAccountNumber;
    private String title;
    private BigDecimal amount;

    public TransferReceiver() {}

    public TransferReceiver(String name, String bankAccountNumber, String title, BigDecimal amount) {
        this.name = name;
        this.bankAccountNumber = bankAccountNumber;
        this.title = title;
        this.amount = amount;
    }
}
