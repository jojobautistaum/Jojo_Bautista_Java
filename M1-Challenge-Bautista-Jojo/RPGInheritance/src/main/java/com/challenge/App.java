package com.challenge;

public class App {
    public static void main(String[] args) {
        Warrior warriorTitan = new Warrior("Titan", 100, 75, 10, 50);
        Farmer farmerJohn = new Farmer("John", 75, 75, 1, 10);
        Constable constableJim = new Constable("Jim", 60, 60, 5, 20, "Saint Paul");

        warriorTitan.attackAnotherCharacter(farmerJohn);
        farmerJohn.attackAnotherCharacter(constableJim);
        constableJim.attackAnotherCharacter(warriorTitan);

    }
}
