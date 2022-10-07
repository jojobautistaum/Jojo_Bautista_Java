package com.challenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterIfTest<ConvertIf> {

    @Test
    public void shouldConvertNumberToCorrespondingMonthInWord() {
        // Arrange
        ConverterIf monthIf = new ConverterIf();
        String expected1 = "January";
        String expected2 = "Invalid Number of Month";
        String expected3 = "September";

        // Act
        String actual1 = monthIf.convertMonth(1);
        String actual2 = monthIf.convertMonth(15);
        String actual3 = monthIf.convertMonth(9);

        // Assert
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    public void shouldConvertNumberToCorrespondingDayInWord() {
        // Arrange
        ConverterIf dayIf = new ConverterIf();
        String expected1 = "Monday";
        String expected2 = "Saturday";
        String expected3 = "Invalid Number of Day";

        // Act
        String actual1 = dayIf.convertDay(2);
        String actual2 = dayIf.convertDay(7);
        String actual3 = dayIf.convertDay(0);

        // Assert
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }


}