package com.sda.spring.entity;

import com.sda.spring.entity.SuspiciousPerson;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuspiciousPersonTest {

    private SuspiciousPerson testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new SuspiciousPerson("James", "Moriarty", "14021420365");
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getFirstName()).isEqualTo("James");
        assertThat(testedObject.getLastName()).isEqualTo("Moriarty");
        assertThat(testedObject.getPesel()).isEqualTo("14021420365");
    }
}
