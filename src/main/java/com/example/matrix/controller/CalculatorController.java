package com.example.matrix.controller;


import calculator.Token;
import calculator.services.FractionToken;
import calculator.services.MatrixToken;
import calculator.services.Variable;
import com.example.matrix.constant.Status;
import com.example.matrix.json.*;
import com.example.matrix.service.CalculatorService;

import com.google.gson.*;
import gsonParser.GsonVariableParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;


@RestController
public class CalculatorController {
    @Autowired
    CalculatorService calculatorService;

    @PostMapping("/calculate")
    public HttpEntity<? extends Serializable> calculate(@RequestBody Map<String, Object> model) throws UnsupportedEncodingException {
        String expression = model.get("expression").toString();
        ArrayList<Variable> variables = parseVariables(model);
        if (expression == null) {
            return new ResponseEntity<>("Missing expression field", HttpStatus.BAD_REQUEST);
        }

        CalculatorService.CalculateResponse response = calculatorService.calculate(expression, variables);


        CalculateResponseJson responseJson = new CalculateResponseJson();

        responseJson.setStatus(response.getStatus().toString().toLowerCase(Locale.ROOT));
        if (response.getStatus() == Status.Error) {
            responseJson.setDetails(response.getDetails());
        } else {
            responseJson.setResult(toTokenResponse(response.getResult()));
        }


        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

    public ArrayList<Variable> parseVariables(Map<String, Object> model) {
        String vars = model.get("variables").toString();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(vars).getAsJsonArray();
        ArrayList<Variable> variables = new ArrayList<>();
        for (JsonElement elem : jsonArray) {
            String type = elem.getAsJsonObject().get("type").getAsString();
            elem.getAsJsonObject().remove("type");
            elem.getAsJsonObject().add("type", new JsonPrimitive(type.substring(0, 1).toUpperCase(Locale.ROOT) + type.substring(1)));
            Variable variable = GsonVariableParser.toVariable(elem.toString());
            variables.add(variable);
        }
        return variables;
    }

    private TokenResponseJson toTokenResponse(Token token) {
        if (Token.Type.Matrix == token.type) {
            MatrixResponseJson matrixResponse = new MatrixResponseJson();
            matrixResponse.main = ((MatrixToken) token).getMatrix().main;
            matrixResponse.augmented = ((MatrixToken) token).getMatrix().augmented;
            matrixResponse.type = token.type.toString().toLowerCase(Locale.ROOT);
            return matrixResponse;
        } else if (Token.Type.Fraction == token.type) {
            FractionResponseJson fractionResponse = new FractionResponseJson();
            fractionResponse.numerator = ((FractionToken) token).getFraction().getNumerator();
            fractionResponse.denominator = ((FractionToken) token).getFraction().getDenominator();
            fractionResponse.type = token.type.toString().toLowerCase(Locale.ROOT);
            return fractionResponse;
        } else {
            assert false;
            return null;
        }

    }


}


