//package gsonParser;
//
//import model.Matrix.Matrix;
//import model.Rational.Rational;
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
//        if (Token.Type.Rational.equals(token.type)){
//            return GsonRationalParser.getJsonRational((Rational) token);
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
