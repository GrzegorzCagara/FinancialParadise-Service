package com.sda.spring;

import com.sda.spring.entity.Account;
import com.sda.spring.entity.Customer;
import com.sda.spring.entity.SuspiciousPerson;
import com.sda.spring.entity.TransferHistory;
import com.sda.spring.repository.SuspiciousPersonRepository;
import com.sda.spring.repository.SuspiciousTransferHistoryRepository;
import com.sda.spring.repository.TransferHistoryRepository;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(JUnitParamsRunner.class)
public class MoneyTransferServiceTest {

    private MoneyTransferService testedObject;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private TransferHistoryRepository mockedTransferHistoryRepository;

    @Mock
    private SuspiciousTransferHistoryRepository mockedSuspiciousTransferHistoryRepository;

    @Mock
    private TransferChecker mockedTransferChecker;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        testedObject = new MoneyTransferService(mockedSuspiciousTransferHistoryRepository, mockedTransferChecker,
                mockedTransferHistoryRepository);
    }

    private Object[][] parametersForSpecificTransfers() {
        return new Object[][]{
                {new BigDecimal("1000"), new BigDecimal("500"), new BigDecimal("500"), new BigDecimal("500"), new BigDecimal("1000")},
                {new BigDecimal("50.87"), new BigDecimal("180.98"), new BigDecimal("20.69"), new BigDecimal("30.18"), new BigDecimal("201.67")},
                {new BigDecimal("0.00"), new BigDecimal("150.00"), new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("150.00")},
//            {new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal("")},
//            {new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal("")},
//            {new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal("")},
        };
    }


    @Test
    @Parameters(method = "parametersForSpecificTransfers")
    public void shouldTransferMoney(BigDecimal accountOneBalance, BigDecimal accountTwoBalance, BigDecimal moneyToTranfer,
                                    BigDecimal expectedAccountOneBalance, BigDecimal expectedAccountTwoBalance) throws Exception {

        Account accountOne = new Account();
        Account accountTwo = new Account();
        Customer customerSender = new Customer();
        customerSender.setAccount(accountOne);
        Customer customerReciever = new Customer();
        customerReciever.setAccount(accountTwo);

        accountOne.setId(1);
        accountTwo.setId(2);
        accountOne.setBalance(accountOneBalance);
        accountTwo.setBalance(accountTwoBalance);

        testedObject.transferMoney(customerSender, customerReciever, moneyToTranfer);

        assertThat(accountOne.getBalance()).isEqualTo(expectedAccountOneBalance);
        assertThat(accountTwo.getBalance()).isEqualTo(expectedAccountTwoBalance);
    }

    @Test
    public void shouldThrowInsufficientFundsExceptionWhenMoneyToTransferBiggerThanAccountOneBalance() throws Exception {

        thrown.expect(InsufficientFundsException.class);

        Customer customerSender = new Customer();
        Account accountOfSender = new Account();
        accountOfSender.setBankAccountNumber("123");
        customerSender.setFirstName("Tom");
        customerSender.setLastName("Hardy");
        customerSender.setPesel("85112045698");
        customerSender.setAccount(accountOfSender);
        customerSender.getAccount().setId(1);
        customerSender.getAccount().setBalance(new BigDecimal("300.00"));

        Customer customerReciever = new Customer();
        Account accountOfReciever = new Account();
        accountOfReciever.setBankAccountNumber("456");
        customerReciever.setFirstName("Cary");
        customerReciever.setLastName("Grant");
        customerSender.setPesel("23051147397");
        customerReciever.setAccount(accountOfReciever);
        customerReciever.getAccount().setId(2);
        customerReciever.getAccount().setBalance(new BigDecimal("800.00"));

        testedObject.transferMoney(customerSender, customerReciever, new BigDecimal("305.00"));
    }
}
