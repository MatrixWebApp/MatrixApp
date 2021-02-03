package gsonParser.converter;

import com.google.gson.*;
import gsonParser.GsonParser;
import matrix.Matrix;
import rational.Rational;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MatrixConverter implements JsonDeserializer<Matrix> {
    @Override
    public Matrix deserialize(JsonElement json, Type type,
                              JsonDeserializationContext context) throws JsonParseException {

        ArrayList<ArrayList<Rational>> main = getMainFromJson(json);
        ArrayList<ArrayList<Rational>> augmented = getAugmentedFromJson(json);

        return new Matrix(main, augmented);
    }

    private ArrayList<ArrayList<Rational>> getMainFromJson(JsonElement jsonElement) {
        // get main JsonArray to convert it to Matrix
        JsonArray jsonArray = jsonElement.getAsJsonObject().get("main").getAsJsonArray();
        ArrayList<ArrayList<Rational>> main = new ArrayList<>();

        for (JsonElement row : jsonArray) {
            main.add(new ArrayList<>());
            for (JsonElement elem : row.getAsJsonArray()) {
                Rational rational = GsonParser.rationalFromJson(elem);
                main.get(main.size() - 1).add(rational);
            }
        }

        return main;
    }

    private ArrayList<ArrayList<Rational>> getAugmentedFromJson(JsonElement jsonElement){
        // get main JsonArray to convert it to Matrix
        JsonArray jsonArray = jsonElement
                .getAsJsonObject()
                .has("augmented") ? jsonElement.getAsJsonObject().get("augmented").getAsJsonArray() :
                                                new JsonArray();

        ArrayList<ArrayList<Rational>> augmented = new ArrayList<>();

        for (JsonElement row : jsonArray) {
            augmented.add(new ArrayList<>());
            for (JsonElement elem : row.getAsJsonArray()) {
                Rational rational = GsonParser.rationalFromJson(elem);
                augmented.get(augmented.size() - 1).add(rational);
            }
        }

        return augmented;

    }
}
