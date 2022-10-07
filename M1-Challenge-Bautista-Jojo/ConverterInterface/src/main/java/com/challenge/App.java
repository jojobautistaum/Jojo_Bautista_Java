package com.challenge;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Random flipIfOrSwitch = new Random();
        Scanner scan = new Scanner(System.in);
        int month;
        int day;

        ConverterIf ifNumberToText = new ConverterIf();
        ConverterSwitch switchNumberToText = new ConverterSwitch();

        // An example of do..while loop usage :-)
        do {
            System.out.println("Enter 0 (ZERO) for both Month and Day to Exit gracefully!");
            boolean invalidInput = true;
            do {
                System.out.print("Enter a number of month[1-12]: ");
                month = Integer.parseInt(scan.nextLine());
                if (month > -1 && month < 13) {
                    invalidInput = false;
                }
            } while (invalidInput);

            invalidInput = true;
            do {
                System.out.print("Enter a number of day[1-7]: ");
                day = Integer.parseInt(scan.nextLine());
                if (day > -1 && day < 8) {
                    invalidInput = false;
                }
            } while (invalidInput);

            if(flipIfOrSwitch.nextBoolean()) {
                System.out.println("IF says, your lucky day are " +
                        ifNumberToText.convertDay(day) + "s of " +
                        ifNumberToText.convertMonth(month));
            } else {
                System.out.println("SWITCH says, your lucky day are " +
                        switchNumberToText.convertDay(day) + "s of " +
                        switchNumberToText.convertMonth(month));
            }
            System.out.println("====================");
        } while (month != 0 && day != 0);

        System.out.println("Good bye!!");
    }

}
