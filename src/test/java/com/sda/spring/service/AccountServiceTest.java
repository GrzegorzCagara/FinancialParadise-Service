package com.sda.spring.service;

import com.sda.spring.entity.Account;
import com.sda.spring.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountServiceTest {

    private AccountServiceImpl testedObject;

    @Mock
    private AccountRepository mockedAccountRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        testedObject = new AccountServiceImpl(mockedAccountRepository);
    }

    @Test
    public void shouldReturnAllAccounts() throws Exception {

        List<Account> accountList = getAllAccounts();

        when(mockedAccountRepository.findAll()).thenReturn(accountList);

        List<Account> foundAccounts = testedObject.getAllAccounts();

        assertThat(foundAccounts).isNotNull();
        assertThat(foundAccounts).isNotEmpty();
        assertThat(foundAccounts.size()).isEqualTo(5);
    }

    @Test
    public void shouldReturnSpecificAccountWhenIdPassed() throws Exception {

        Account account = new Account();
        account.setId(19);

        when(mockedAccountRepository.findAccountById(19)).thenReturn(account);

        Account foundAccount = testedObject.getAccountById(19);

        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount).isEqualTo(account);
        assertThat(foundAccount.getId()).isEqualTo(19);
    }

    @Test
    public void shouldAddAccount() throws Exception {

        Account account = new Account();
        when(mockedAccountRepository.save(account)).thenReturn(account);

        testedObject.save(account);

        verify(mockedAccountRepository, times(1)).save(account);
    }

    @Test
    public void shouldDeleteAccountWhenIdPassed() throws Exception {

        Account account = new Account();
        account.setId(17);

        doNothing().when(mockedAccountRepository).deleteAccountById(17);

        testedObject.deleteAccountById(17);

        verify(mockedAccountRepository, times(1)).deleteAccountById(17);
    }

    @Test
    public void shouldUpdateAccount() throws Exception {

        Account account = new Account();
        when(mockedAccountRepository.save(account)).thenReturn(account);

        testedObject.updateAccount(account);

        verify(mockedAccountRepository, times(1)).save(account);
    }

    private List<Account> getAllAccounts(){
        List<Account> result = new ArrayList<>();
        result.add(new Account());
        result.add(new Account());
        result.add(new Account());
        result.add(new Account());
        result.add(new Account());
        return result;
    }
}
