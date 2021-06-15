package gsonParser.converter;


import calculator.Token;
import calculator.services.MatrixToken;
import calculator.services.FractionToken;
import calculator.services.Variable;
import com.google.gson.*;
import gsonParser.GsonParser;
import matrix.Matrix;
import fraction.Fraction;

import java.lang.reflect.Type;

public class VariableConverter implements JsonDeserializer<Variable>, JsonSerializer<Variable> {

    public Variable deserialize(JsonElement json, Type type,
                                JsonDeserializationContext context) throws JsonParseException {
        String name = getName(json);
        Token.Type varType = Token.Type.valueOf(getType(json));
        Token value = getTokenByType(json, varType);

        return new Variable(name, value);
    }

    public String getName(JsonElement json){
        return json.getAsJsonObject().get("name").getAsString();
    }
    public String getType(JsonElement json){
        return json.getAsJsonObject().get("type").getAsString();
    }
    public Token getTokenByType(JsonElement json, Token.Type type){

        JsonObject value = json.getAsJsonObject().get("value").getAsJsonObject();

        switch (type){
            case Matrix:
                return new MatrixToken(GsonParser.matrixFromJson(value));
            case Fraction:
                return new FractionToken(GsonParser.fractionFromJson(value));
            default:
                throw new JsonParseException("no such type");
        }
    }



    public JsonElement serialize(Variable variable, Type type,
                                 JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("name", variable.name);
        object.addProperty("type", variable.getValue().type.toString());

        switch (variable.getValue().type){
            case Matrix:
                MatrixToken matrixToken = (MatrixToken) variable.getValue();
                return GsonParser.toJson(matrixToken.getMatrix());
            case Fraction:
                FractionToken fractionToken = (FractionToken) variable.getValue();
                return GsonParser.toJson(fractionToken.getFraction());
            default:
                throw new JsonParseException("no such type");
        }
    }

}
