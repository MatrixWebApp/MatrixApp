package com.example.matrix.json;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class TokenResponse implements Serializable {
    @Getter
    @Setter
    public String type;
}
