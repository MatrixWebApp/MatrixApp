//package gsonParser;
//
//import com.google.gson.*;
//import calculator.view.Variable;
//import view.gsonParser.converter.VariableConverter;
//
//public class GsonVariableParser {
//
//    public static Gson gsonParser;
//
//    static {
//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(Variable.class, new VariableConverter());
//        gsonParser = builder.create();
//    }
//
//    static public Variable getVariable(String json){
//        return gsonParser.fromJson(json, Variable.class);
//    }
//    static public String getJsonVariable(Variable variable){
//        return gsonParser.toJson(variable);
//    }
//
//}
//
//
