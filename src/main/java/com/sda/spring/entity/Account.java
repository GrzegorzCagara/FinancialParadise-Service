package com.sda.spring.entity;

import com.sda.spring.enums.Currency;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bankAccountNumber;
    private BigDecimal balance;
    private Currency currency = Currency.PLN;

    public Account() {
    }

    public Account(String bankAccountNumber, BigDecimal balance, Currency currency) {
        this.bankAccountNumber = bankAccountNumber;
        this.balance = balance;
        this.currency = currency;
    }
}
