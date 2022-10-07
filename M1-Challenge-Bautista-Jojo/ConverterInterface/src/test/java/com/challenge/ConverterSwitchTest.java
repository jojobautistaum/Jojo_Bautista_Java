package com.challenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterSwitchTest {

    @Test
    public void willConvertNumberToCorrespondingMonthInWord() {
        ConverterSwitch monthSwitch = new ConverterSwitch();
        assertEquals("January", monthSwitch.convertMonth(1));
        assertEquals("Invalid Number of Month", monthSwitch.convertMonth(15));
        assertEquals("September", monthSwitch.convertMonth(9));
    }

    @Test
    public void willConvertNumberToCorrespondingDayInWord() {
        ConverterSwitch daySwitch = new ConverterSwitch();
        assertEquals("Monday", daySwitch.convertDay(2));
        assertEquals("Saturday", daySwitch.convertDay(7));
        assertEquals("Invalid Number of Day", daySwitch.convertDay(0));
    }
}