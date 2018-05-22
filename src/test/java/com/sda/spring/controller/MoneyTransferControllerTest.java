package com.sda.spring.controller;

import com.sda.spring.dto.CustomerWithTransferReceiver;
import com.sda.spring.dto.TransferReceiver;
import com.sda.spring.entity.Account;
import com.sda.spring.entity.Customer;
import com.sda.spring.service.CustomerServiceImpl;
import com.sda.spring.service.MoneyTransferServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MoneyTransferControllerTest {

    private MoneyTransferController testedObject;

    @Mock
    private CustomerWithTransferReceiver customerWithTransferReceiver;

    @Mock
    private MoneyTransferServiceImpl mockedMoneyTransferService;

    @Mock
    private CustomerServiceImpl mockedCustomerService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        testedObject = new MoneyTransferController(mockedMoneyTransferService, mockedCustomerService);
    }

    @Test
    public void shouldTransferMoney() throws Exception {

        ResponseEntity<?> desiredResponse = new ResponseEntity<>(HttpStatus.OK);
        Customer customerSender = new Customer();
        Account accountSender = new Account();
        accountSender.setBankAccountNumber("123");
        customerSender.setAccount(accountSender);

        Customer customerReciever = new Customer();
        Account accountReciever = new Account();
        accountReciever.setBankAccountNumber("456");
        customerReciever.setAccount(accountReciever);

        TransferReceiver transferReceiver = new TransferReceiver();
        transferReceiver.setBankAccountNumber("456");
        transferReceiver.setAmount(new BigDecimal("25.00"));
        transferReceiver.setTitle("Title");

        when(mockedCustomerService.getCustomerByAccountNumber("123")).thenReturn(customerSender);

        doNothing().when(mockedMoneyTransferService).transferMoney(customerSender, customerReciever,
                new BigDecimal("25.00"), "Title");
        when(customerWithTransferReceiver.getTransferReceiver()).thenReturn(transferReceiver);
        when(customerWithTransferReceiver.getCustomer()).thenReturn(customerSender);
        when(mockedCustomerService.getCustomerByAccountNumber("456")).thenReturn(customerReciever);

        ResponseEntity<?> response = testedObject.sendMoney(customerWithTransferReceiver);

        assertThat(response).isEqualTo(desiredResponse);
        verify(mockedMoneyTransferService, times(1)).transferMoney(customerSender, customerReciever,
                new BigDecimal("25.00"), "Title");
    }
}

