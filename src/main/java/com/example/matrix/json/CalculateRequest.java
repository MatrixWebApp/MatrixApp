package com.example.matrix.json;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

public class CalculateRequest implements Serializable {
    @Getter
    @Setter
    String expression;
    @Getter
    @Setter
    ArrayList<Variable> variables;
}
