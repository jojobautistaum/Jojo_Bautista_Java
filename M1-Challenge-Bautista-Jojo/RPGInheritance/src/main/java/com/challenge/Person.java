package com.challenge;

public class Person {
    protected String name;
    protected int health = 100;

    protected int stamina;
    protected int strength;

    protected int attackPower;
    protected int speed;
    protected boolean arrested = false;
    protected boolean running = false;

    public Person(String name, int stamina, int strength, int attackPower, int speed) {
        this.name = name;
        this.stamina = stamina;
        this.strength = strength;
        this.attackPower = attackPower;
        this.speed = speed;
    }

    public void characterCurrentHealthStat(Person personStat) {
        System.out.println("******* " + personStat.name + "'s Stat *******");
        System.out.println("Health: " + personStat.health);
        System.out.println("Stamina: " + personStat.stamina);
        System.out.println("Strength: " + personStat.strength);
        if (personStat instanceof Warrior) {
            System.out.println("Shield Strength: " + ((Warrior) personStat).getShieldStrength());
        }
    }

    // Attacker: will lose strength based on its attackPower
    // Defender: will lose health based on attacker's attackPower
    // Warrior as Defender: will use their shieldStrength first before their health
    public void attackAnotherCharacter(Person defender) {
        if (defender.health == 0) {
            System.out.println("You cannot attack the dead " + defender.name);
            return;
        }

        if (this.strength == 0) {
            System.out.println(this.name + " is too weak to attack");
            return;
        } else if (this.strength < this.attackPower) {
            this.attackPower = this.strength;
            this.strength = 0;
        } else {
            this.strength -= this.attackPower;
        }

        if (defender.stamina > 0 && defender.health < this.attackPower) {
            defender.health += defender.stamina;
            defender.stamina = 0;
        }

        // Warrior will use their shieldStrength first when defending before using their health
        if (defender instanceof Warrior) {
            if (((Warrior)defender).getShieldStrength() < this.attackPower){
                defender.health += ((Warrior)defender).getShieldStrength();
                ((Warrior)defender).setShieldStrength(0);
                defender.health -= this.attackPower;
            } else if (((Warrior)defender).getShieldStrength() == 0){
                defender.health -= this.attackPower;
            } else {
                ((Warrior) defender).setShieldStrength(((Warrior) defender).getShieldStrength() - this.attackPower);
            }
        } else {
            defender.health -= this.attackPower;
        }

        if(defender.health < 0) defender.health = 0;

        if(defender.health == 0) System.out.println(defender.name + " is now dead!");

        System.out.println(this.name + " ATTACKED " + defender.name);
        characterCurrentHealthStat(defender);
        characterCurrentHealthStat(this);
        System.out.println("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isArrested() {
        return arrested;
    }

    public void setArrested(boolean arrested) {
        this.arrested = arrested;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", stamina=" + stamina +
                ", strength=" + strength +
                ", attackPower=" + attackPower +
                ", speed=" + speed +
                ", arrested=" + arrested +
                ", running=" + running +
                '}';
    }
}

