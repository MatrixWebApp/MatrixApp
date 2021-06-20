package com.example.matrix.json;

import calculator.Token;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class CalculateResponse implements Serializable{
    public CalculateResponse(){}
    public enum Status{
        ok,
        error
    }
    @Getter
    @Setter
    Status status;
    @Getter
    @Setter
    TokenResponse result;

}
