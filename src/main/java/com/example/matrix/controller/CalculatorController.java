package com.example.matrix.controller;


import calculator.Token;
import calculator.services.MatrixToken;
import calculator.services.Variable;
import com.example.matrix.json.CalculateResponse;
import com.example.matrix.json.MatrixResponse;
import com.example.matrix.json.ResultToken;
import com.example.matrix.json.TokenResponse;
import com.example.matrix.service.CalculatorService;

import com.github.jknack.handlebars.internal.antlr.misc.Pair;
import com.google.gson.*;
import gsonParser.GsonParser;
import gsonParser.GsonVariableParser;
import io.swagger.util.Json;
import lombok.Getter;
import lombok.Setter;
import matrix.Matrix;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
        if (expression == null){
            return new ResponseEntity<>("Missing expression field", HttpStatus.BAD_REQUEST);
        }

        Pair<Token, String> resultPair = calculatorService.calculate(expression, variables);

        CalculateResponse response = new CalculateResponse();

        response.setStatus(CalculateResponse.Status.valueOf(resultPair.b.toLowerCase(Locale.ROOT)));
        response.setResult(toTokenResponse(resultPair.a));

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    public ArrayList<Variable>  parseVariables(Map<String, Object> model) {
        String vars = model.get("variables").toString();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(vars).getAsJsonArray();
        ArrayList<Variable> variables = new ArrayList<>();
        for (JsonElement elem : jsonArray){
            String type = elem.getAsJsonObject().get("type").getAsString();
            elem.getAsJsonObject().remove("type");
            elem.getAsJsonObject().add("type", new JsonPrimitive(type.substring(0, 1).toUpperCase(Locale.ROOT) + type.substring(1)));
            Variable variable = GsonVariableParser.toVariable(elem.toString());
            variables.add(variable);
        }
        return variables;
    }

    private TokenResponse toTokenResponse(Token token){
        if (token.type.equals(Token.Type.Matrix)){
            MatrixResponse matrixResponse = new MatrixResponse();
            matrixResponse.main = ((MatrixToken) token).getMatrix().main;
            matrixResponse.augmented = ((MatrixToken) token).getMatrix().augmented;
            matrixResponse.type = token.type.toString().toLowerCase(Locale.ROOT);
            return matrixResponse;
        }
        else return null;
        // TODO is not implemented fraction case
    }


}


