package gsonParser.converter;



import com.google.gson.*;
import fraction.Fraction;

import java.lang.reflect.Type;

public class FractionConverter implements JsonDeserializer<Fraction>, JsonSerializer<Fraction> {

    public Fraction deserialize(JsonElement json, Type type,
                                JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        int numerator = object.get("numerator").getAsInt();
        int denominator = object.has("denominator") ? object.get("denominator").getAsInt():
                                                                  1;
        return new Fraction(numerator, denominator);
    }

    public JsonElement serialize(Fraction fraction, Type type,
                                 JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("numerator", fraction.getNumerator());
        object.addProperty("denominator", fraction.getDenominator());
        return object;
    }
}
