package gsonParser.converter;



import com.google.gson.*;
import rational.Rational;

import java.lang.reflect.Type;

public class RationalConverter implements JsonDeserializer<Rational>, JsonSerializer<Rational> {

    public Rational deserialize(JsonElement json, Type type,
                                JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        int numerator = object.get("numerator").getAsInt();
        int denominator = object.has("denominator") ? object.get("denominator").getAsInt():
                                                                  1;
        return new Rational(numerator, denominator);
    }

    public JsonElement serialize(Rational rational, Type type,
                                 JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("numerator", rational.getNumerator());
        object.addProperty("denominator", rational.getDenominator());
        return object;
    }
}
