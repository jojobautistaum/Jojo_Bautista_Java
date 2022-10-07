package com.challenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmerTest {
    private Farmer farmerAttacker;
    private Farmer farmerDefender;
    private Warrior warriorPerson;

    @Before
    public void setUp() {
        farmerAttacker = new Farmer("Tim", 75, 75, 1, 10);
        farmerDefender = new Farmer("Tim", 75, 75, 1, 10);
        warriorPerson = new Warrior("Titan", 100, 75, 10, 50);
    }

    @Test
    public void setFarmerAttackerWillLoseSomeStrengthbyItsAttactPower() {
        // Arrange
        int expectedStrength = 75 - 1;

        // Act
        farmerAttacker.attackAnotherCharacter(warriorPerson);
        int actualStrength = farmerAttacker.getStrength();

        // Assert
        assertEquals(expectedStrength, actualStrength);
    }

    @Test
    public void setFarmerDefenderWillLoseSomeHealthByAttackersAttackPower() {
        // Arrange
        int expectedHealth = 100 - 10;

        // Act
        warriorPerson.attackAnotherCharacter(farmerDefender);
        int actualHealth = farmerDefender.getHealth();

        // Assert
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    public void shouldUseStaminaToReplenishHealthWhenHealthHasLessThanAttackersAttackPower() {
        // Arrange
        farmerDefender.setHealth(2);
        int expectedHealth = 2 + 75 - 10;
        int expectedStamina = 0;

        // Act
        warriorPerson.attackAnotherCharacter(farmerDefender);
        int actualHealth = farmerDefender.getHealth();


        // Assert
        assertEquals(expectedHealth, actualHealth);
        assertEquals(expectedStamina, farmerDefender.getStamina());
    }

    @Test
    public void setFarmerDefenderWillNotLoseHealthWhenStaminaAndHealthAreZeroes() {
        // Arrange
        int expectedHealth = 0;

        // Act
        farmerDefender.setHealth(0);
        farmerDefender.setStamina(0);
        warriorPerson.attackAnotherCharacter(farmerDefender);
        int actualHealth = farmerDefender.getHealth();

        // Assert
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    public void setFarmerAttackerCannotAttackWhenStrengthIsZeroDefenderHealthRemainTheSame() {
        // Arrange
        int expectedHealth = 100;

        // Act
        farmerAttacker.setStrength(0);
        farmerAttacker.attackAnotherCharacter(warriorPerson);
        int actualHealth = warriorPerson.getHealth();

        // Assert
        assertEquals(expectedHealth, actualHealth);
    }
}