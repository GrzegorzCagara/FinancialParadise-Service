package com.sda.spring.entity;

import com.sda.spring.entity.SuspiciousTransferHistory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SuspiciousTransferHistoryTest {

    private SuspiciousTransferHistory testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new SuspiciousTransferHistory("123", "456",
    "Title", LocalDateTime.of(2017,11,8,15,23), new BigDecimal("21.99"));
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getBankAccountNumberFrom()).isEqualTo("123");
        assertThat(testedObject.getBankAccountNumberTo()).isEqualTo("456");
        assertThat(testedObject.getTitle()).isEqualTo("Title");
        assertThat(testedObject.getDate()).isEqualTo(LocalDateTime.of(2017,11,8,15,23));
        assertThat(testedObject.getAmount()).isEqualTo(new BigDecimal("21.99"));
    }
}
