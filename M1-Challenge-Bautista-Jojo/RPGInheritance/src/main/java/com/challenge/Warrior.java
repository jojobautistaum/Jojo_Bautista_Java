package com.challenge;

public class Warrior extends Person {
    private int shieldStrength = 100;

    public Warrior(String name, int stamina, int strength, int attackPower, int speed) {
        super(name, stamina, strength, attackPower, speed);
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "shieldStrength=" + shieldStrength +
                '}';
    }
}
