//package gsonParser;
//
//import model.Matrix.Matrix;
//import model.Fraction.Fraction;
//import model.calculator.Token;
//
//public class GsonTokenParser {
////
////    static public Matrix getToken(String jsonString){
////        Matrix matrix = gsonParser.fromJson(jsonString, Matrix.class);
////        return matrix;
////    }
//
//    static public String getJsonToken(Token token){
//        if (Token.Type.Fraction.equals(token.type)){
//            return GsonFractionParser.getJsonFraction((Fraction) token);
//        }
//        else if (Token.Type.Matrix.equals(token.type)){
//            return GsonParser.getJsonMatrix((Matrix) token);
//        }
//        else{
//            throw new IllegalArgumentException("undefined token");
//        }
//    }
//
//}
