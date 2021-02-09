package gsonParser;

import calculator.services.Variable;
import com.google.gson.*;
import gsonParser.converter.MatrixConverter;
import gsonParser.converter.RationalConverter;
import gsonParser.converter.VariableConverter;
import matrix.Matrix;
import rational.Rational;

// parser for Matrix, Rational
public class GsonParser {
    private static Gson gsonMatrixParser;
    private static Gson gsonRationalParser;
    private static Gson gsonVariableParser;

    private static JsonParser parser;

    static {
        gsonRationalParser = new GsonBuilder()
                .registerTypeAdapter(Rational.class, new RationalConverter())
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

    static public Rational rationalFromJson(JsonElement json){
        return gsonRationalParser.fromJson(json, Rational.class);
    }

    static public JsonElement toJson(Matrix matrix){
        String json = gsonMatrixParser.toJson(matrix);
        return parser.parse(json);
    }

    static public JsonElement toJson(Rational rational){
        String json = gsonRationalParser.toJson(rational);
        return parser.parse(json);
    }

    public static Variable variableFromJson(JsonElement json) {
        return gsonVariableParser.fromJson(json, Variable.class);
    }
}
