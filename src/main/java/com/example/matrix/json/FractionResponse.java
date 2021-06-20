package com.example.matrix.json;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class FractionResponse extends TokenResponse implements Serializable {
    @Getter
    @Setter
    public int numerator;
    @Getter
    @Setter
    public int denominator;
}
