package com.north47.springboottesting.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.north47.springboottesting.utils.Validators.isEmailValid;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorsTest {
    @Test
    public void testEmailValidator() {
        assertThat(isEmailValid("valid@47northlabs.com")).isTrue();
        assertThat(isEmailValid("invalid47northlabs.com")).isFalse();
        assertThat(isEmailValid("invalid@47")).isFalse();
    }
}
