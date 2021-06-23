package com.example.matrix.service;

import calculator.Calculator;
import calculator.Token;
import calculator.services.Variable;
import com.example.matrix.constant.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CalculatorService {

    public String OK = "OK";

    public CalculateResponse calculate(String expression, ArrayList<Variable> variables) {
        Calculator calculator = new Calculator(variables);
        Token token = calculator.evaluate(expression);
        CalculateResponse response = new CalculateResponse();

        response.status = Status.Ok;
        response.result = token;
        if (!OK.equals(calculator.status)){
            response.status = Status.Error;
            response.details = calculator.status;
        }

        return response;
    }

    public class CalculateResponse {
        @Getter
        @Setter
        Token result;
        @Getter
        @Setter
        Status status;
        @Getter
        @Setter
        String details;
    }
}
