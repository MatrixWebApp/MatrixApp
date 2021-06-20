package com.example.matrix.json;

import calculator.Token;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class CalculateResponse implements Serializable{
    public CalculateResponse(){}
    public enum Status{
        ok,
        error
    }
    // TODO change corresponding with API
    @Getter
    @Setter
    String status;
    @Getter
    @Setter
    TokenResponse result;

}
