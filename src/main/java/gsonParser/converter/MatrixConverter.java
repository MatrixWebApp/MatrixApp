package gsonParser.converter;

import com.google.gson.*;
import gsonParser.GsonParser;
import matrix.Matrix;
import fraction.Fraction;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MatrixConverter implements JsonDeserializer<Matrix> {
    @Override
    public Matrix deserialize(JsonElement json, Type type,
                              JsonDeserializationContext context) throws JsonParseException {

        ArrayList<ArrayList<Fraction>> main = getMainFromJson(json);
        ArrayList<ArrayList<Fraction>> augmented = getAugmentedFromJson(json);

        return new Matrix(main, augmented);
    }

    static private ArrayList<ArrayList<Fraction>> getMainFromJson(JsonElement jsonElement) {
        // get main JsonArray to convert it to Matrix
        JsonArray jsonArray = jsonElement.getAsJsonObject().get("main").getAsJsonArray();
        ArrayList<ArrayList<Fraction>> main = new ArrayList<>();

        for (JsonElement row : jsonArray) {
            main.add(new ArrayList<>());
            for (JsonElement elem : row.getAsJsonArray()) {
                Fraction fraction = GsonParser.fractionFromJson(elem);
                main.get(main.size() - 1).add(fraction);
            }
        }

        return main;
    }

    static private ArrayList<ArrayList<Fraction>> getAugmentedFromJson(JsonElement jsonElement){
        // get main JsonArray to convert it to Matrix
        JsonArray jsonArray = jsonElement
                .getAsJsonObject()
                .has("augmented") ? jsonElement.getAsJsonObject().get("augmented").getAsJsonArray() :
                                                new JsonArray();

        ArrayList<ArrayList<Fraction>> augmented = new ArrayList<>();

        for (JsonElement row : jsonArray) {
            augmented.add(new ArrayList<>());
            for (JsonElement elem : row.getAsJsonArray()) {
                Fraction fraction = GsonParser.fractionFromJson(elem);
                augmented.get(augmented.size() - 1).add(fraction);
            }
        }

        return augmented;

    }
}
