package com.challenge;

public class Farmer extends Person{
    private boolean harvesting = false;
    private boolean plowing = false;

    public Farmer(String name, int stamina, int strength, int attackPower, int speed) {
        super(name, stamina, strength, attackPower, speed);
    }

    public boolean isHarvesting() {
        return harvesting;
    }

    public void setHarvesting(boolean harvesting) {
        this.harvesting = harvesting;
    }

    public boolean isPlowing() {
        return plowing;
    }

    public void setPlowing(boolean plowing) {
        this.plowing = plowing;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "harvesting=" + harvesting +
                ", plowing=" + plowing +
                '}';
    }
}
