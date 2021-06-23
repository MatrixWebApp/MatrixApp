package com.example.matrix.json;

import fraction.Fraction;

import java.io.Serializable;
import java.util.ArrayList;

public class MatrixResponseJson extends TokenResponseJson implements Serializable  {
    public ArrayList<ArrayList<Fraction>> main = new ArrayList<>();
    public ArrayList<ArrayList<Fraction>> augmented = new ArrayList<>();
}
