package com.challenge.monthandmathservice.controller;

import com.challenge.monthandmathservice.model.Month;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class MonthController {
    private static Month convertMonth (String number) {
        Month monthResult = new Month();
        monthResult.setNumber(number);
        monthResult.setName(monthResult.convertMonthNumber());
        return monthResult;
    }

    @RequestMapping(value = "/month/{monthNumber}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Month getMonthNumber (@PathVariable String monthNumber) {
        return convertMonth(monthNumber);
    }

    @RequestMapping(value = "/randomMonth", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Month getRandomMonth () {
        Random randomMonthNumber = new Random();
        return convertMonth(Integer.toString(randomMonthNumber.nextInt(12) + 1));
    }

}
