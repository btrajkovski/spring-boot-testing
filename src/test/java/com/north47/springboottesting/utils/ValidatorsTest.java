package com.north47.springboottesting.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorsTest {
    @Test
    public void testEmailValidator() {
        assertThat(Validators.isEmailValid("valid@47northlabs.com")).isTrue();
        assertThat(Validators.isEmailValid("invalid47northlabs.com")).isFalse();
        assertThat(Validators.isEmailValid("invalid@47")).isFalse();
    }
}