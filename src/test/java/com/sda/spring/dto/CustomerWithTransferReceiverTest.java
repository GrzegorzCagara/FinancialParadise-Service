package com.sda.spring.dto;

import com.sda.spring.dto.CustomerWithTransferReceiver;
import com.sda.spring.dto.TransferReceiver;
import com.sda.spring.entity.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerWithTransferReceiverTest {

    private CustomerWithTransferReceiver testedObject;
    private Customer customer;
    private TransferReceiver transferReceiver;

    @Before
    public void setUp() throws Exception {
        testedObject = new CustomerWithTransferReceiver();
        customer = new Customer();
        testedObject.setCustomer(customer);
        testedObject.setTransferReceiver(transferReceiver);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getCustomer()).isEqualTo(customer);
        assertThat(testedObject.getTransferReceiver()).isEqualTo(transferReceiver);
    }
}
