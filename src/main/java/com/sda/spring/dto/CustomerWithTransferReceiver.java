package com.sda.spring.dto;

import com.sda.spring.entity.Customer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerWithTransferReceiver {
    private Customer customer;
    private TransferReceiver transferReceiver;

    public CustomerWithTransferReceiver(){}

    public CustomerWithTransferReceiver(Customer customer, TransferReceiver transferReceiver) {
        this.customer = customer;
        this.transferReceiver = transferReceiver;
    }
}
