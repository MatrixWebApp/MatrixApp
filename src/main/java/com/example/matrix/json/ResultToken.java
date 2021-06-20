package com.example.matrix.json;

import calculator.Token;
import com.google.gson.JsonElement;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.entity.StringEntity;

import java.io.Serializable;

public class ResultToken implements Serializable {
    @Getter
    @Setter
    TokenResponse value;
}
