package com.challenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConstableTest {

    private Constable constableAttacker;
    private Constable constableDefender;
    private Warrior warriorPerson;

    @Before
    public void setUp() {
        constableAttacker = new Constable("Jack", 60, 60, 5, 20, "Chicago Suburbs");
        constableDefender = new Constable("Jack", 60, 60, 5, 20, "Chicago Suburbs");
        warriorPerson = new Warrior("Titan", 100, 75, 10, 50);
    }

    @Test
    public void setConstableAttackerWillLoseSomeStrengthbyItsAttactPower() {
        // Arrange
        int expectedStrength = 60 - 5;

        // Act
        constableAttacker.attackAnotherCharacter(warriorPerson);
        int actualStrength = constableAttacker.getStrength();


        // Assert
        assertEquals(expectedStrength, actualStrength);
    }

    @Test
    public void setConstableDefenderWillLoseSomeHealthByAttackersAttackPower() {
        // Arrange
        int expectedHealth = 100 - 10;

        // Act
        warriorPerson.attackAnotherCharacter(constableDefender);
        int actualHealth = constableDefender.getHealth();

        // Assert
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    public void shouldUseStaminaToReplenishHealthWhenHealthHasLessThanAttackersAttackPower() {
        // Arrange
        constableDefender.setHealth(2);
        int expectedHealth = 2 + 60 - 10;
        int expectedStamina = 0;

        // Act
        warriorPerson.attackAnotherCharacter(constableDefender);
        int actualHealth = constableDefender.getHealth();
        int actualStamina = constableDefender.getStamina();

        // Assert
        assertEquals(expectedHealth, actualHealth);
        assertEquals(expectedStamina, actualStamina);
    }

    @Test
    public void setConstableDefenderWillNotLoseHealthWhenStaminaAndHealthAreZeroes() {
        // Arrange
        int expectedHealth = 0;

        // Act
        constableDefender.setHealth(0);
        constableDefender.setStamina(0);
        warriorPerson.attackAnotherCharacter(constableDefender);
        int actualHealth = constableDefender.getHealth();

        // Assert
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    public void setConstableAttackerCannotAttackWhenStrengthIsZeroDefenderHealthRemainTheSame() {
        // Arrange
        int expectedHealth = 100;

        // Act
        constableAttacker.setStrength(0);
        constableAttacker.attackAnotherCharacter(warriorPerson);
        int actualHealth = warriorPerson.getHealth();

        // Assert
        assertEquals(expectedHealth, actualHealth);
    }

}