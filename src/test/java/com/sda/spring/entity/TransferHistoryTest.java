package com.sda.spring.entity;

import com.sda.spring.entity.TransferHistory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TransferHistoryTest {

    private TransferHistory testedObject;

    private final static Integer ID = 89;
    private final static String ACCOUNT_NO_FROM = "02456798712355698666677772";
    private final static String ACCOUNT_NO_TO = "02456798712355698666677813";
    private final static LocalDateTime DATETIME = LocalDateTime.of(2016,4,9,7,0);
    private final static BigDecimal AMOUNT = new BigDecimal("564987.24");

    @Before
    public void setUp() throws Exception {
        testedObject = new TransferHistory();
        testedObject.setId(ID);
        testedObject.setBankAccountNumberTo(ACCOUNT_NO_TO);
        testedObject.setBankAccountNumberFrom(ACCOUNT_NO_FROM);
        testedObject.setDate(DATETIME);
        testedObject.setAmount(AMOUNT);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(89);
        assertThat(testedObject.getBankAccountNumberFrom()).isEqualTo("02456798712355698666677772");
        assertThat(testedObject.getBankAccountNumberTo()).isEqualTo("02456798712355698666677813");
        assertThat(testedObject.getDate()).isEqualTo(LocalDateTime.of(2016,4,9,7,0));
        assertThat(testedObject.getAmount()).isEqualTo(new BigDecimal("564987.24"));
    }
}
