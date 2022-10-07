package com.challenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WarriorTest {
    private Warrior warriorAttacker;
    private Warrior warriorDefender;
    private Constable constablePerson;

    @Before
    public void setUp() {
        warriorAttacker = new Warrior("Titan", 100, 75, 10, 50);
        warriorDefender = new Warrior("Titan", 100, 75, 10, 50);
        constablePerson = new Constable("Tom", 60, 75, 5, 20, "Dakota County");
    }

    @Test
    public void setWarriorAttackerWillLoseSomeStrengthbyItsAttactPower() {
        // Arrange
        int expectedStrength = 75 - 10;

        // Act
        warriorAttacker.attackAnotherCharacter(constablePerson);
        int actualStrength = warriorAttacker.getStrength();

        // Assert
        assertEquals(expectedStrength, actualStrength);
    }

    @Test
    public void setWarriorDefenderWillLoseSomeShieldStrengthByAttackersAttackPower() {
        // Arrange
        int expectedShieldStrength = 100 - 5;

        // Act
        constablePerson.attackAnotherCharacter(warriorDefender);
        int actualShieldStrength = warriorDefender.getShieldStrength();

        // Assert
        assertEquals(expectedShieldStrength, actualShieldStrength);
    }

    @Test
    public void shouldUseHealthWithRemainingShieldStrengthWhenShieldStrengthIsLessThanAttackersAttackPower() {
        // Arrange
        warriorDefender.setShieldStrength(2);
        int expectedHealth = 2 + 100 - 5;
        int expectedShieldStrength = 0;

        // Act
        constablePerson.attackAnotherCharacter(warriorDefender);
        int actualHealth = warriorDefender.getHealth();
        int actualShieldStrength = warriorDefender.getShieldStrength();

        // Assert
        assertEquals(expectedHealth, actualHealth);
        assertEquals(expectedShieldStrength, actualShieldStrength);
    }

    @Test
    public void shouldUseStaminaToReplenishHealthWhenHealthHasLessThanAttackersAttackPowerAndShieldStrengthIsZero() {
        // Arrange
        warriorDefender.setHealth(2);
        warriorDefender.setShieldStrength(0);
        int expectedHealth = 2 + 100 - 5;
        int expectedStamina = 0;
        int expectedShieldStrength = 0;

        // Act
        constablePerson.attackAnotherCharacter(warriorDefender);
        int actualHealth = warriorDefender.getHealth();
        int actualStamina = warriorDefender.getStamina();
        int actualShieldStrength = warriorDefender.getShieldStrength();

        // Assert
        assertEquals(expectedHealth, actualHealth);
        assertEquals(expectedStamina, actualStamina);
        assertEquals(expectedShieldStrength, actualShieldStrength);
    }

    @Test
    public void setWariorDefenderWillNotLoseHealthWhenHisStaminaAndHealthAreZeroes() {
        // Arrange
        int expectedHealth = 0;

        // Act
        warriorDefender.setHealth(0);
        warriorDefender.setStamina(0);
        constablePerson.attackAnotherCharacter(warriorDefender);
        int actualHealth = warriorDefender.getHealth();

        // Assert
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    public void setWarriorAttackerCannotAttackWhenStrengthIsZeroDefenderHealthRemainTheSame() {
        // Arrange
        int expectedHealth = 100;

        // Act
        warriorAttacker.setStrength(0);
        warriorAttacker.attackAnotherCharacter(constablePerson);
        int actualHealth = warriorAttacker.getHealth();

        // Assert
        assertEquals(expectedHealth, actualHealth);
    }
}