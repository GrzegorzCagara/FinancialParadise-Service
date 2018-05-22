package com.sda.spring.controller;

import com.sda.spring.entity.Account;
import com.sda.spring.entity.TransferHistory;
import com.sda.spring.service.TransferHistoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransferHistoryControllerTest {

    private TransferHistoryController testedObject;

    @Mock
    private TransferHistoryServiceImpl mockedTransferHistoryService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        testedObject = new TransferHistoryController(mockedTransferHistoryService);
    }

    @Test
    public void shouldReturnEntireTransferHistoryForSpecificAccountWhenBankAccountNumberPassed() throws Exception {

        Account account = new Account();
        account.setBankAccountNumber("321");

        when(mockedTransferHistoryService.getTransferHistoryForSpecificAccount("321"))
                .thenReturn(getAllTransferHistoriesForAccount321());

        List<TransferHistory> transferHistoryList = testedObject.getAllTransferHistoryForSpecificAccount("321");

        assertThat(transferHistoryList).isNotNull();
        assertThat(transferHistoryList).isNotEmpty();
        assertThat(transferHistoryList.size()).isEqualTo(6);
    }

    private List<TransferHistory> getAllTransferHistoriesForAccount321(){
        List<TransferHistory> result = new ArrayList<>();
        result.add(new TransferHistory());
        result.add(new TransferHistory());
        result.add(new TransferHistory());
        result.add(new TransferHistory());
        result.add(new TransferHistory());
        result.add(new TransferHistory());
        return result;
    }
}
