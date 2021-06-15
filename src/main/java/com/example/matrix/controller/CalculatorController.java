package com.example.matrix.controller;

import com.example.matrix.Service.CalculatorService;
import com.example.matrix.json.CalculateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody CalculateRequest request) {
        return new ResponseEntity<String>(request.getExpression(), HttpStatus.OK);
    }
    @GetMapping("/calculate")
    public ResponseEntity<String> calculateget(@RequestBody CalculateRequest request) {
        return new ResponseEntity<String>(request.getExpression(), HttpStatus.OK);
    }
}


