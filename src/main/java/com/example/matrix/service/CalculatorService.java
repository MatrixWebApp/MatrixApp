package com.example.matrix.service;

import calculator.Calculator;
import calculator.Token;
import calculator.services.MatrixToken;
import calculator.services.Variable;
import com.github.jknack.handlebars.internal.antlr.misc.Pair;
import gsonParser.GsonParser;
import gsonParser.GsonVariableParser;
import matrix.Matrix;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Service
public class CalculatorService {
    public Pair<Token, String> calculate(String expression, ArrayList<Variable> variables) {
        Calculator calculator = new Calculator(variables);
        Token token = calculator.evaluate(expression);
        // change corresponding with openAPI
        return new Pair<>(token, calculator.status);
    }
}
