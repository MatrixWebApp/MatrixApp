package gsonParser;

import calculator.services.Variable;
import com.google.gson.*;
import gsonParser.converter.MatrixConverter;
import gsonParser.converter.FractionConverter;
import gsonParser.converter.VariableConverter;
import matrix.Matrix;
import fraction.Fraction;

// parser for Matrix, Fraction
public class GsonParser {
    private static final Gson gsonMatrixParser;
    private static final Gson gsonFractionParser;
    private static final Gson gsonVariableParser;

    private static final JsonParser parser;

    static {
        gsonFractionParser = new GsonBuilder()
                .registerTypeAdapter(Fraction.class, new FractionConverter())
                .create();
        gsonMatrixParser = new GsonBuilder()
                .registerTypeAdapter(Matrix.class, new MatrixConverter())
                .create();
        gsonVariableParser = new GsonBuilder()
                .registerTypeAdapter(Variable.class, new VariableConverter())
                .create();

        parser = new JsonParser();
    }

    static public Matrix matrixFromJson(JsonElement json){
        return gsonMatrixParser.fromJson(json, Matrix.class);
    }

    static public Fraction fractionFromJson(JsonElement json){
        return gsonFractionParser.fromJson(json, Fraction.class);
    }

    static public JsonElement toJson(Matrix matrix){
        String json = gsonMatrixParser.toJson(matrix);
        return parser.parse(json);
    }

    static public JsonElement toJson(Fraction fraction){
        String json = gsonFractionParser.toJson(fraction);
        return parser.parse(json);
    }

    public static Variable variableFromJson(JsonElement json) {
        return gsonVariableParser.fromJson(json, Variable.class);
    }
}
