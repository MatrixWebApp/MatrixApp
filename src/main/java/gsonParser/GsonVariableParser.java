package gsonParser;

import calculator.services.Variable;
import com.google.gson.*;
import gsonParser.converter.VariableConverter;

public class GsonVariableParser {

    public static Gson gsonParser;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Variable.class, new VariableConverter());
        gsonParser = builder.create();
    }

    static public Variable toVariable(String json){
        return gsonParser.fromJson(json, Variable.class);
    }
    static public String toJson(Variable variable){
        return gsonParser.toJson(variable);
    }

}


