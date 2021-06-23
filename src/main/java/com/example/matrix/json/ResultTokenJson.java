package com.example.matrix.json;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class ResultTokenJson implements Serializable {
    @Getter
    @Setter
    TokenResponseJson value;
}
