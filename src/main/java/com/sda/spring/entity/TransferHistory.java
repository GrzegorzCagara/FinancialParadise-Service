package com.sda.spring.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sda.spring.handler.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transfer_history")
public class TransferHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bank_account_number_from")
    private String bankAccountNumberFrom;

    @Column(name = "bank_account_number_to")
    private String bankAccountNumberTo;

    private String title;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime date;

    private BigDecimal amount;

    public TransferHistory() {
    }

    public TransferHistory(Builder builder){
        this.bankAccountNumberFrom = builder.bankAccountNumberFrom;
        this.bankAccountNumberTo = builder.bankAccountNumberTo;
        this.title = builder.title;
        this.date = builder.date;
        this.amount = builder.amount;
    }

    public TransferHistory(String bankAccountNumberFrom, String bankAccountNumberTo, String title, LocalDateTime date,
                           BigDecimal amount) {
        this.bankAccountNumberFrom = bankAccountNumberFrom;
        this.bankAccountNumberTo = bankAccountNumberTo;
        this.title = title;
        this.date = date;
        this.amount = amount;
    }

    public static class Builder {
        private String bankAccountNumberFrom;
        private String bankAccountNumberTo;
        private String title;
        private LocalDateTime date;
        private BigDecimal amount;

        public Builder withBankAccountNumberFrom(String bankAccountNumberFrom) {
            this.bankAccountNumberFrom = bankAccountNumberFrom;
            return this;
        }

        public Builder withBankAccountNumberTo(String bankAccountNumberTo) {
            this.bankAccountNumberTo = bankAccountNumberTo;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public TransferHistory build(){
            return new TransferHistory(this);
        }
    }
}
