package com.challenge.pointofsale;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IceCream {
    private String flavor;
    private static final double price = 2.50d;

    private static final DecimalFormat df = new DecimalFormat("$0.00");
    private int numberOfScoops;

    public IceCream(String flavor) {
        this.flavor = flavor;
        this.numberOfScoops = 1;
    }

    public void oneScoop() {
        System.out.println("Enjoy your one scoop of " + getFlavor() + " ice cream!");
        System.out.println("Price: " + df.format(price));
    }

    // Each additional scoop is 80% of the original one scoop price
    // You can only add 1 up to 3 more scoops of the same flavor
    public void addScoop(int addedNumberOfScoop) {
        if (addedNumberOfScoop > 0 && addedNumberOfScoop < 4) {
            System.out.println("Enjoy your additional " + addedNumberOfScoop + " scoop(s) of " + this.flavor + " ice cream!");
            System.out.println("Price: " + df.format((price + (price * addedNumberOfScoop * 0.80d))));
        } else {
            System.out.println("You can only add between 1-3 additional scoops.");
        }
    }

    // 2 to 3 different flavors
    // Each scoop with regular price
    public static void multiFlavorUpToThree(List<String> flavors) {
        if(flavors == null || flavors.isEmpty() || flavors.size() > 3 || flavors.size() < 2){
            System.out.println("Please select 2-3 flavors where each flavor is one scoop");
        } else {
            System.out.print("Enjoy your this yummy mix of flavors: ");
            if (flavors.size() == 2) {
                System.out.println(flavors.get(0) + " and " + flavors.get(1));
            } else {
                System.out.println(flavors.get(0) + ", " + flavors.get(1)+ " and " + flavors.get(2));
            }
            System.out.println("Price: " + df.format(price * flavors.size()));
        }
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return "IceCream{" +
                "flavor='" + flavor + '\'' +
                ", numberOfScoops=" + numberOfScoops +
                '}';
    }
}
