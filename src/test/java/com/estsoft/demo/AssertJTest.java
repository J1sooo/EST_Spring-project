package com.estsoft.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJTest {
    @Test
    public void addTest() {
        int a = 1;
        int b = 2;
        int result = 4;
        // Assertions 생략
        assertThat(a+b).isEqualTo(result);
    }
}
