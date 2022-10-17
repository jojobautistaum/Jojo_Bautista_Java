package com.challenge.monthandmathservice.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Month {
    private String number;
    private String name;

    public String convertMonthNumber() {
        String monthNumber = getNumber();
        switch (monthNumber) {
            case "1":
                return "January";
            case "2":
                return "February";
            case "3":
                return "March";
            case "4":
                return "April";
            case "5":
                return "May";
            case "6":
                return "June";
            case "7":
                return "July";
            case "8":
                return "August";
            case "9":
                return "September";
            case "10":
                return "October";
            case "11":
                return "November";
            case "12":
                return "December";
            default:
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Input 'month number' is out of range. Valid number is between 1 to 12.");
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
