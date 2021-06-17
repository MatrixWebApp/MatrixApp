package com.example.matrix.controller;

import com.example.matrix.Service.CalculatorService;
import com.example.matrix.json.CalculateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @CrossOrigin
    @PostMapping("/calculate")
    public ResponseEntity<String> calculate() {
        return new ResponseEntity<String>( HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/calculate")
    public ResponseEntity<String> calculateget(Map<String, Object> request) {
        System.out.println(request);
        return new ResponseEntity<String>( HttpStatus.OK);
    }
}


