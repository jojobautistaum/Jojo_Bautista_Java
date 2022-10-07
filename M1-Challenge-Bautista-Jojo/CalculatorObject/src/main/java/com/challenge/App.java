package com.challenge;

import java.text.DecimalFormat;

public class App {
    public static void main(String[] args) {
        CalculatorObject calc = new CalculatorObject();
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Perform calculations of two int numbers:");
        System.out.println("\t1 + 1 = " + calc.add(1, 1));
        System.out.println("\t23 - 52 = " + calc.subtract(23, 52));
        System.out.println("\t34 * 2 = " + calc.multiply(34, 2));
        System.out.println("\t12 / 3 = " + calc.divide(12, 3));
        System.out.println("\t12 / 7 = " + calc.divide(12, 7));

        System.out.println("\nPerform calculations of two double numbers:");
        System.out.println("\t3.4 + 2.3 = " + df.format(calc.add(3.4, 2.3)));
        System.out.println("\t6.7 * 4.4 = " + df.format(calc.multiply(6.7, 4.4)));
        System.out.println("\t5.5 - 0.5 = " + df.format(calc.subtract(5.5, 0.5)));
        System.out.println("\t10.8 / 2.2 = " + df.format(calc.divide(10.8, 2.2)));
    }
}
