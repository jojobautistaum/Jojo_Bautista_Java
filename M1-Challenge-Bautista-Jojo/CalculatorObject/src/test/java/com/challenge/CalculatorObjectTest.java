package com.challenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorObjectTest {
    private CalculatorObject calc;

    @Before
    public void setUp() {
        calc = new CalculatorObject();
    }

    @Test
    public void shouldReturnSumOfTwoInts() {
        // Arrange
        String failMessage = "Expected to add two int numbers.";
        int expected = 11;

        // Act
        int actual = calc.add(5, 6);

        // Assert
        assertEquals(failMessage, expected, actual);
    }

    @Test
    public void shouldReturnSumOfTwoDoubles() {
        // Arrange
        String failMessage = "Expected to add two double numbers.";
        double expected = 5.7d;

        // Act
        double actual = calc.add(3.4d, 2.3d);

        // Assert
        assertEquals(failMessage, expected, actual, .0001);
    }

    @Test
    public void shouldReturnDifferenceOfTwoInts() {
        // Arrange
        String failMessage = "Expected to subtract two int numbers.";
        int expected = -29;

        // Act
        int actual = calc.subtract(23, 52);

        // Assert
        assertEquals(failMessage, expected, actual);
    }

    @Test
    public void shouldReturnDifferenceOfTwoDoubles() {
        // Arrange
        String failMessage = "Expected to subtract two double numbers.";
        double expected = 1.1d;

        // Act
        double actual = calc.subtract(4.5d, 3.4d);

        // Assert
        assertEquals(failMessage, expected, actual, .0001);
    }

    @Test
    public void shouldReturnProductOfTwoInts() {
        // Arrange
        String failMessage = "Expected to multiply two int numbers.";
        int expected = 21;

        // Act
        int actual = calc.multiply(3, 7);

        // Assert
        assertEquals(failMessage, expected, actual);
    }

    @Test
    public void shouldReturnProductOfTwoDoubles() {
        // Arrange
        String failMessage = "Expected to multiply two double numbers.";
        double expected = 10.15d;

        // Act
        double actual = calc.multiply(3.5d, 2.9d);

        // Assert
        assertEquals(failMessage, expected, actual, .0001);
    }

    @Test
    public void shouldReturnQuotientOfTwoInts() {
        // Arrange
        String failMessage = "Expected to divide two int numbers.";
        int expected = 2;

        // Act
        int actual = calc.divide(15, 7);

        // Assert
        assertEquals(failMessage, expected, actual);
    }

    @Test
    public void shouldReturnQuotientOfTwoDoubles() {
        // Arrange
        String failMessage = "Expected to divide two double numbers.";
        double expected = 2.2d;

        // Act
        double actual = calc.divide(5.5d, 2.5d);

        // Assert
        assertEquals(failMessage, expected, actual, .0001);
    }
}