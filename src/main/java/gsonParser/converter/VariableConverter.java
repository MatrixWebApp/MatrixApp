//package gsonParser.converter;
//
//
//import com.google.gson.*;
//import model.Matrix.Matrix;
//import model.Rational.Rational;
//import model.calculator.Token;
//import calculator.view.Variable;
//import gsonParser.GsonParser;
//
//import java.lang.reflect.Type;
//
//public class VariableConverter implements JsonDeserializer<Variable>, JsonSerializer<Variable> {
//
//    public Variable deserialize(JsonElement json, Type type,
//                                JsonDeserializationContext context) throws JsonParseException {
//        JsonObject object = json.getAsJsonObject();
//
//        String name = object.get("name").getAsString();
//        Token value = "Matrix".compareTo(object.get("type").getAsString()) == 0 ? GsonParser.matrixFromJson(object.get("value").toString()):
//                                                                                  GsonRationalParser.getRational(object.get("value").toString());
//        return new Variable(name,value.type, value);
//    }
//    public JsonElement serialize(Variable variable, Type type,
//                                 JsonSerializationContext context) {
//        JsonObject object = new JsonObject();
//        object.addProperty("name", variable.name);
//        object.addProperty("type", variable.type.name());
//        if ("Matrix".compareTo(variable.type.name()) == 0) {
//            object.addProperty("value", GsonParser.getJsonMatrix((Matrix) variable.value));
//        }
//        if ("Rational".compareTo(variable.type.name()) == 0) {
//            object.addProperty("value", GsonRationalParser.getJsonRational((Rational) variable.value));
//        }
//        return object;
//    }
//
//}
