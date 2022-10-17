package com.challenge.monthandmathservice.controller;

import com.challenge.monthandmathservice.model.MathSolution;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MathSolutionController {

    public static MathSolution performCalculation(String operation, MathSolution calculationResult) {
        if (calculationResult.getOperand1() == null && calculationResult.getOperand2() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Missing 'operand1' and 'operand2' or its value is null. Make sure to use lower case 'operand' for the keys");
        }
        if (calculationResult.getOperand1() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Missing 'operand1'  or its value is null. Make sure to use lower case 'operand' for the key");
        }
        if (calculationResult.getOperand2() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Missing 'operand2'  or its value is null. Make sure to use lower case 'operand' for the key");
        }
        calculationResult.setOperation(operation);
        calculationResult.setAnswer(calculationResult.calculateNumbers());
        return calculationResult;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution addNumbers (@RequestBody MathSolution mathResult) {
        return performCalculation("add", mathResult);
    }

    @RequestMapping(value = "/subtract", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution subtractNumbers (@RequestBody MathSolution mathResult) {
        return performCalculation("subtract", mathResult);
    }

    @RequestMapping(value = "/multiply", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution multiplyNumbers (@RequestBody MathSolution mathResult) {
        return performCalculation("multiply", mathResult);
    }

    @RequestMapping(value = "/divide", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution divideNumbers (@RequestBody MathSolution mathResult) {
        return performCalculation("divide", mathResult);
    }
}
