package com.challenge;

public class Constable extends Person {
    private String jurisdiction;

    public Constable(String name, int stamina, int strength, int attackPower, int speed, String jurisdiction) {
        super(name, stamina, strength, attackPower, speed);
        this.jurisdiction = jurisdiction;
    }

    public void arrestAnotherCharacter(Person detainee) {
        detainee.arrested = true;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    @Override
    public String toString() {
        return "Constable{" +
                "jurisdiction='" + jurisdiction + '\'' +
                '}';
    }
}
