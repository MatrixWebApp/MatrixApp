//package gsonParser.converter;
//
//import com.google.gson.*;
//import calculator.view.UserPage;
//import calculator.view.Variable;
//import gsonParser.GsonVariableParser;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//
//public class UserPageConverter implements JsonDeserializer<UserPage>, JsonSerializer<UserPage> {
//
//    public UserPage deserialize(JsonElement json, Type type,
//                                JsonDeserializationContext context) throws JsonParseException {
//
//        JsonObject jsonObject = json.getAsJsonObject();
//
//        int list = jsonObject.get("list").getAsInt();
//        String expression = jsonObject.get("expression").getAsString();
//
//        ArrayList<Variable> variables = new ArrayList<>();
//
//        JsonElement jsonArrayMatrix = jsonObject.get("variables");
//        for (int i = 0; i < jsonArrayMatrix.getAsJsonArray().size(); i++){
//            variables.add(GsonVariableParser.getVariable(jsonArrayMatrix.getAsJsonArray().get(i).toString()));
//        }
//        return new UserPage(list,expression, variables);
//    }
//
//    public JsonElement serialize(UserPage userPage, Type type,
//                                 JsonSerializationContext context) {
//        JsonObject object = new JsonObject();
//        object.addProperty("list", userPage.list);
//        object.addProperty("expression", userPage.expression);
//        object.addProperty("variables", GsonVariableParser.gsonParser.toJson(userPage.variables));
//        return object;
//    }
//
//}
