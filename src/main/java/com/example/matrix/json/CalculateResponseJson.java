package com.example.matrix.json;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class CalculateResponseJson implements Serializable{
    public CalculateResponseJson(){}
    @Getter
    @Setter
    String status;
    @Getter
    @Setter
    TokenResponseJson result;
    @Getter
    @Setter
    String details;

}
