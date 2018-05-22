package com.sda.spring.service;

import com.sda.spring.entity.TransferHistory;
import com.sda.spring.repository.TransferHistoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransferHistoryServiceTest {

    private TransferHistoryServiceImpl testedObject;

    @Mock
    private TransferHistoryRepository mockedTransferHistoryRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        testedObject = new TransferHistoryServiceImpl(mockedTransferHistoryRepository);
    }

    @Test
    public void shouldReturnAllTransferHistories() throws Exception {

        when(mockedTransferHistoryRepository.findAll()).thenReturn(getAllTransferHistories());

        List<TransferHistory> thList = testedObject.getAllTransferHistories();

        assertThat(thList).isNotEmpty();
        assertThat(thList).isNotNull();
        assertThat(thList.size()).isEqualTo(4);
    }

    @Test
    public void shouldAddNewTransferHistory() throws Exception {

        TransferHistory transferHistory = new TransferHistory();

        when(mockedTransferHistoryRepository.save(transferHistory)).thenReturn(transferHistory);

        testedObject.addTransferHistory(transferHistory);

        verify(mockedTransferHistoryRepository, times(1)).save(transferHistory);
    }

    @Test
    public void shouldReturnTransferHistoryForSpecificAccountWhenBankAccountNumberPassed() throws Exception {

        when(mockedTransferHistoryRepository.findTransferHistoriesByBankAccountNumberFromOrderByDateDesc("123"))
                .thenReturn(getAllTransferHistories());

        List<TransferHistory> transferHistory = testedObject.getTransferHistoryForSpecificAccount("123");

        assertThat(transferHistory).isNotNull();
        assertThat(transferHistory).isNotEmpty();
        assertThat(transferHistory.size()).isEqualTo(4);
        assertThat(transferHistory.get(0).getAmount()).isEqualTo(new BigDecimal("25.00"));
    }

    private List<TransferHistory> getAllTransferHistories(){
        List<TransferHistory> result = new ArrayList<>();
        TransferHistory th1 = new TransferHistory("123","456","Title",
                LocalDateTime.of(2011,1,23,11,58), new BigDecimal("25.00"));
        TransferHistory th2 = new TransferHistory("123","456","Title",
                LocalDateTime.of(2011,1,23,12,8), new BigDecimal("725.09"));
        TransferHistory th3 = new TransferHistory("123","456","Title",
                LocalDateTime.of(2011,11,2,11,58), new BigDecimal("5.10"));
        TransferHistory th4 = new TransferHistory("123","456","Title",
                LocalDateTime.of(2011,5,3,11,58), new BigDecimal("85.47"));
        result.add(th1);
        result.add(th2);
        result.add(th3);
        result.add(th4);
        return result;
    }
}