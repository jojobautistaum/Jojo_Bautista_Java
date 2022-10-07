package com.challenge.factory;

import java.text.DecimalFormat;
import java.util.List;

public class IceCream {
    private String flavor;
    private String size;
    private double salePrice;

    public IceCream(String flavor, String size, double salePrice) {
        this.flavor = flavor;
        this.size = size;
        this.salePrice = salePrice;
    }

    public double orderTotalPrice(int quantity) {
       return this.salePrice * quantity;
    }

    public void orderDetail(String storeName, int quantity) {
        DecimalFormat df = new DecimalFormat("$0.00");
        System.out.println(storeName + " ordered " + quantity + " " + getSize() + " of " + getFlavor() + " ice cream");
        System.out.println("Total Price: " + df.format(orderTotalPrice(quantity)));
    }
    public static void bestSellerFlavor(List<String> flavors) {
        System.out.print("Best flavors of all time: ");
        for (String flavor: flavors) {
            if (flavors.get(0) == flavor){
                System.out.print(flavor);
            } else if(flavors.get(flavors.size() - 1) == flavor) {
                System.out.println(" and " + flavor);
            } else {
                System.out.print(", " + flavor);
            }
        }
    }

    public String getFlavor() {
        return flavor;
    }

    public String getSize() {
        return size;
    }
}
