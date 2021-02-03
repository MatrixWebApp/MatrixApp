//package gsonParser;
//
//import com.google.gson.*;
//import calculator.view.UserPage;
//import view.gsonParser.converter.UserPageConverter;
//
//public class GsonUserPageParser {
//
//    private static Gson gsonParser;
//
//    static {
//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(UserPage.class, new UserPageConverter());
//        gsonParser = builder.create();
//    }
//
//    static public UserPage getUserPage(String json){ return gsonParser.fromJson(json, UserPage.class);
//    }
//    static public String getJsonUserPage(UserPage userPage){ return gsonParser.toJson(userPage);
//    }
//
//}
//
//
