package com.sda.spring.service;

import com.sda.spring.entity.Account;
import com.sda.spring.entity.Customer;
import com.sda.spring.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CustomerServiceTest {

    private CustomerServiceImpl testedObject;

    @Mock
    private CustomerRepository mockedCustomerRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        testedObject = new CustomerServiceImpl(mockedCustomerRepository);
    }

    @Test
    public void shouldReturnAllCustomers() throws Exception {

        when(mockedCustomerRepository.findAll()).thenReturn(getAllCustomers());

        List<Customer> customerList = testedObject.getAllCustomers();

        assertThat(customerList).isNotNull();
        assertThat(customerList).isNotEmpty();
        assertThat(customerList.size()).isEqualTo(4);
    }

    @Test
    public void shouldAddCustomer() throws Exception {

        Customer customerToAdd = new Customer();

        when(mockedCustomerRepository.save(customerToAdd)).thenReturn(customerToAdd);

        testedObject.save(customerToAdd);

        verify(mockedCustomerRepository, times(1)).save(customerToAdd);
    }

    @Test
    public void shouldReturnSpecificCustomerWhenIdPassed() throws Exception {

        Customer customer = new Customer();
        customer.setId(69);

        when(mockedCustomerRepository.findCustomerById(69)).thenReturn(customer);

        Customer foundCustomer = testedObject.getCustomerById(69);

        assertThat(foundCustomer).isNotNull();
        assertThat(customer).isEqualTo(customer);
        assertThat(foundCustomer.getId()).isEqualTo(69);
    }

    @Test
    public void shouldReturnSpecificCustomerWhenBankAccountNumberPassed() throws Exception {

        Customer customer = new Customer();
        Account account = new Account();
        account.setBankAccountNumber("789");
        customer.setAccount(account);

        when(mockedCustomerRepository.findCustomerByAccountBankAccountNumber("789")).thenReturn(customer);

        Customer foundCustomer = testedObject.getCustomerByAccountNumber("789");

        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getAccount().getBankAccountNumber()).isEqualTo("789");
    }

    @Test
    public void shouldReturnSpecificCustomerWhenEmailPassed() throws Exception {

        Customer customer = new Customer();
        customer.setEmail("srakapraptaka@jahu.kom");

        when(mockedCustomerRepository.findCustomerByEmail("srakapraptaka@jahu.kom")).thenReturn(customer);

        Customer foundCustomer = testedObject.getCustomerByEmail("srakapraptaka@jahu.kom");

        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer).isEqualTo(customer);
        assertThat(foundCustomer.getEmail()).isEqualTo("srakapraptaka@jahu.kom");
    }

    @Test
    public void shouldDeleteCustomerWhenIdPassed() throws Exception {

        Customer customerToDelete = new Customer();
        customerToDelete.setId(88);

        doNothing().when(mockedCustomerRepository).deleteCustomerById(88);

        testedObject.deleteCustomerById(88);

        verify(mockedCustomerRepository, times(1)).deleteCustomerById(88);
    }

    private List<Customer> getAllCustomers(){
        List<Customer> result = new ArrayList<>();
        result.add(new Customer());
        result.add(new Customer());
        result.add(new Customer());
        result.add(new Customer());
        return result;
    }
}
