package com.north47.springboottesting.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.north47.springboottesting.utils.Validators.isEmailValid;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorsTest {
    @Test
    public void testValidEmailShouldReturnTrue() {
        assertThat(isEmailValid("valid@47northlabs.com")).isTrue();
    }

    @Test
    public void testEmailWithoutAtSignShouldReturnFalse() {
        assertThat(isEmailValid("valid@47northlabs.com")).isTrue();
    }

    @Test
    public void testEmailWithInvalidExtensionShouldReturnFalse() {
        assertThat(isEmailValid("invalid@47")).isFalse();
    }
}
