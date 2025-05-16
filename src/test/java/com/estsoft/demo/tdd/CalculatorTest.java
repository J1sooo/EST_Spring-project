package com.estsoft.demo.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    public void testSum() {
        assertEquals(3, sum(1, 2));
        assertEquals(100, sum(50, 50));
        assertEquals(500, sum(200, 300));
    }

    private int sum(int a, int b) {
        return a + b;
    }
}

