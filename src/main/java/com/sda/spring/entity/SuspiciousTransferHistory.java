package com.sda.spring.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sda.spring.handler.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class SuspiciousTransferHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bankAccountNumberFrom;
    private String bankAccountNumberTo;
    private String title;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime date;
    private BigDecimal amount;

    public SuspiciousTransferHistory() {
    }

    public SuspiciousTransferHistory(Builder builder){
        this.bankAccountNumberFrom = builder.bankAccountNumberFrom;
        this.bankAccountNumberTo = builder.bankAccountNumberTo;
        this.title = builder.title;
        this.date = builder.date;
        this.amount = builder.amount;
    }

    public SuspiciousTransferHistory(String bankAccountNumberFrom, String bankAccountNumberTo, String title,
                                     LocalDateTime date, BigDecimal amount) {
        this.bankAccountNumberFrom = bankAccountNumberFrom;
        this.bankAccountNumberTo = bankAccountNumberTo;
        this.title = title;
        this.date = date;
        this.amount = amount;
    }
    
    public static class Builder{
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

        public Builder withTitle(String title){
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

        public SuspiciousTransferHistory build(){
            return new SuspiciousTransferHistory(this);
        }
    }
}


