package com.sda.spring.entity;

import com.sda.spring.entity.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    private Customer testedObject;

    private final static Integer ID = 69;
    private final static String FIRSTNAME = "Andrzej";
    private final static String LASTNAME = "Gablota";
    private final static String PESEL = "67112456789";
    private final static String EMAIL = "andrew@yahoo.com";
    private final static String PASSWORD = "isthebest";

    @Before
    public void setUp() throws Exception {
        testedObject = new Customer();
        testedObject.setId(ID);
        testedObject.setFirstName(FIRSTNAME);
        testedObject.setLastName(LASTNAME);
        testedObject.setPesel(PESEL);
        testedObject.setEmail(EMAIL);
        testedObject.setPassword(PASSWORD);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(69);
        assertThat(testedObject.getFirstName()).isEqualTo("Andrzej");
        assertThat(testedObject.getLastName()).isEqualTo("Gablota");
        assertThat(testedObject.getPesel()).isEqualTo("67112456789");
        assertThat(testedObject.getEmail()).isEqualTo("andrew@yahoo.com");
        assertThat(testedObject.getPassword()).isEqualTo("isthebest");
    }
}
