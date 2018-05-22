package com.sda.spring;

import com.sda.spring.entity.Account;
import com.sda.spring.entity.Customer;
import com.sda.spring.entity.SuspiciousPerson;
import com.sda.spring.entity.TransferHistory;
import com.sda.spring.enums.Currency;
import com.sda.spring.repository.SuspiciousPersonRepository;
import com.sda.spring.service.TransferHistoryServiceImpl;
import com.sda.spring.utils.TransferChecker;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransferCheckerTest {

    private TransferChecker testedObject;

    @Mock
    private SuspiciousPersonRepository mockedSuspiciousPersonRepository;

    @Mock
    private TransferHistoryServiceImpl mockedTransferHistoryService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        testedObject = new TransferChecker(mockedSuspiciousPersonRepository, mockedTransferHistoryService);
    }

    @Test
    public void shouldReturnTrueWhenSuspiciousPersonIsIdentified() throws Exception {

        Customer sender = new Customer("Cpt", "Hook", "12345678910", "hook@yahoo.com",
                "dupa123", new Account("123", new BigDecimal("1500.89"), Currency.PLN));
        Customer reciever = new Customer("Ali", "Baba", "56345678911", "ali@gmail.com",
                "twojastara123", new Account("456", new BigDecimal("2784.13"), Currency.PLN));
        SuspiciousPerson badGuy = new SuspiciousPerson("Cpt", "Hook", "12345678910");

        when(mockedSuspiciousPersonRepository.findSuspiciousPersonByFirstNameAndLastNameAndPesel("Cpt", "Hook",
                "12345678910")).thenReturn(badGuy);
        when(mockedTransferHistoryService.getTransferHistoryForSpecificAccount("123"))
                .thenReturn(getTransferHistoriesList());

        Boolean suspiciousPerson = testedObject.isTransferSuspicious(sender, reciever, new BigDecimal("250.00"));

        assertThat(suspiciousPerson).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseWhenSuspiciousPersonIsNotIdentified() throws Exception {

        Customer sender = new Customer("Tom", "Hanks", "44445678910", "forrest@gmail.com",
                "bubbagump", new Account("123", new BigDecimal("543.79"), Currency.PLN));
        Customer reciever = new Customer("Jenny", "Gump", "22345678911", "jennyg@gmail.com",
                "chocolate", new Account("654", new BigDecimal("2784.13"), Currency.PLN));

        when(mockedSuspiciousPersonRepository.findSuspiciousPersonByFirstNameAndLastNameAndPesel("Tom", "Hanks",
                "44445678910")).thenReturn(null);
        when(mockedTransferHistoryService.getTransferHistoryForSpecificAccount("123"))
                .thenReturn(getTransferHistoriesList());

        Boolean suspiciousPerson = testedObject.isTransferSuspicious(sender, reciever, new BigDecimal("140.50"));

        assertThat(suspiciousPerson).isEqualTo(false);
    }

    private List<TransferHistory> getTransferHistoriesList() {
        List<TransferHistory> histories = new LinkedList<>();
        histories.add(new TransferHistory("123", "456", "money",
                LocalDateTime.of(2017, 11, 28, 22, 33), new BigDecimal("355.00")));
        histories.add(new TransferHistory("123", "456", "bucks",
                LocalDateTime.of(2017, 10, 28, 22, 33), new BigDecimal("855.22")));
        histories.add(new TransferHistory("123", "456", "rent",
                LocalDateTime.of(2017, 11, 28, 22, 33), new BigDecimal("145.37")));
        return histories;
    }
}
