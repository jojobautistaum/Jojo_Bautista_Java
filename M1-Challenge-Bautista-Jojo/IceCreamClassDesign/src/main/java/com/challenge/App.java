package com.challenge;

import com.challenge.pointofsale.IceCream;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // pointofsale package
        System.out.println("*********** Point of sale Ice Cream **********");
        IceCream creamyVanilla = new IceCream("Vanilla");

        creamyVanilla.oneScoop();
        creamyVanilla.addScoop(2);

        List<String> mixFlavor = new ArrayList<>();
        mixFlavor.add("Chocolate Chip");
        mixFlavor.add("Mint");
        mixFlavor.add("Strawberry");
        IceCream.multiFlavorUpToThree(mixFlavor);

        // factory package
        System.out.println("\n************ Factory Ice Cream *************");
        com.challenge.factory.IceCream vanillaOnePint = new com.challenge.factory.IceCream("Vanilla", "1 Pint", 2.99d);
        com.challenge.factory.IceCream vanillaOneGallon = new com.challenge.factory.IceCream("Vanilla", "1 Gallon", 10.99d);
        com.challenge.factory.IceCream vanillaHalfGallon = new com.challenge.factory.IceCream("Vanilla", "Half Gallon", 5.99d);
        com.challenge.factory.IceCream vanillaOneQuart = new com.challenge.factory.IceCream("Vanilla", "1 Quart", 4.99d);

        vanillaHalfGallon.orderDetail("Aldi", 10);
        List<String> bestFlavors = new ArrayList<>();
        bestFlavors.add("Vanilla Bean");
        bestFlavors.add("Neopolitan");
        bestFlavors.add("Rocky Road");
        com.challenge.factory.IceCream.bestSellerFlavor(bestFlavors);
    }
}
