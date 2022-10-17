package com.challenge.monthandmathservice.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public class MathSolution {
    private String operand1;
    private String operand2;
    private String operation;

    private Integer answer;

    public Integer calculateNumbers() {
        String operation = getOperation();
        Integer op1 = Integer.parseInt(this.operand1);
        Integer op2 = Integer.parseInt(this.operand2);
        switch(operation) {
            case "add":
                return op1 + op2;
            case "subtract":
                return op1 - op2;
            case "multiply":
                return op1 * op2;
            case "divide":
                return op1 / op2;
            default:
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unknown Arithmetic Operation Specified");
        }
    }

    public String getOperand1() {
        return operand1;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathSolution that = (MathSolution) o;
        return Objects.equals(operand1, that.operand1) && Objects.equals(operand2, that.operand2) && Objects.equals(operation, that.operation) && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand1, operand2, operation, answer);
    }

    @Override
    public String toString() {
        return "MathSolution{" +
                "operand1=" + operand1 +
                ", operand2=" + operand2 +
                ", operation='" + operation + '\'' +
                ", answer=" + answer +
                '}';
    }
}
