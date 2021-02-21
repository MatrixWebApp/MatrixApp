package calculator;


import calculator.services.MatrixToken;
import calculator.services.RationalToken;
import matrix.Matrix;

import java.util.Objects;

public class Token {
    public Type type;

    public enum Type{
        Matrix,
        Rational
    }

    public Token add(Token token) {
        return null;
    }

    public Token sub(Token token) throws IllegalArgumentException {
        new IllegalArgumentException("this method is not implemented");
        return null;
    }

    public Token mlp(Token token) throws IllegalArgumentException {
        return null;
    }

    public Token pow(Token token) throws IllegalArgumentException {
        return null;
    }

    public Token getInverseMatrix() {
        return null;
    }

    public Token getDeterminant() {
        return null;
    }

    public Token getTransposeMatrix() {
        return null;
    }

    public Token getTriangleMatrix() {
        return null;
    }


    public Token getRankOfMatrix() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;

        if (type != token.type) return false;

        switch (type){
            case Matrix:
                return ((MatrixToken) this).getMatrix().equals(((MatrixToken) token).getMatrix());
            case Rational:
                return ((RationalToken) this).getRational().equals(((RationalToken) token).getRational());
            default:
                return false;
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
    // binary



//        if ((Type.Matrix.compareTo(type) == 0) && (Type.Matrix.compareTo(token.type) == 0)) {
//            return ((Matrix) this).add((Matrix) token);
//        }
//        if ((Type.Rational.compareTo(type) == 0) && (Type.Rational.compareTo(token.type) == 0)) {
//            return ((Rational) this).add((Rational) token);
//        }
//
//        if ((Type.Matrix.compareTo(type) == 0) && ((Type.Rational).compareTo(token.type) == 0)){
//            return ((Matrix) this).add((Rational) token);
//        }
//
//        throw new IllegalArgumentException("invalid operands in add");
    }

//    public Token sub(Token token) throws IllegalArgumentException{
//        if ((Type.Matrix.compareTo(this.type) == 0) && (Type.Matrix.compareTo(token.type) == 0)) {
//            return ((Matrix) this).sub((Matrix) token);
//        }
//        if ((type.compareTo(Type.Rational) == 0) && (token.type.compareTo(Type.Rational) == 0)) {
//            return ((Rational) this).sub((Rational) token);
//        }
//        throw new IllegalArgumentException("invalid operands in sub");
//    }
//
//    public Token mlp(Token token) throws IllegalArgumentException{
//        if ((Type.Matrix.compareTo(type) == 0) && (Type.Matrix.compareTo(token.type) == 0)) {
//            return ((Matrix) this).mlp((Matrix) token);
//        }
//        if ((Type.Rational.compareTo(type) == 0) && (Type.Rational.compareTo(token.type) == 0)) {
//            return ((Rational) this).mlp((Rational) token);
//        }
//        if ((Type.Matrix.compareTo(type) == 0) && ((Type.Rational).compareTo(token.type) == 0)){
//            return ((Matrix) this).mlp((Rational) token);
//        }
//
//        throw new IllegalArgumentException("invalid operands in mlp");
//    }
//
//    public Token div(Token token) throws IllegalArgumentException{
//        if ((Type.Matrix.compareTo(type) == 0) && (Type.Matrix.compareTo(token.type) == 0)) {
//            return ((Matrix) this).div((Matrix) token);
//        }
//        if ((Type.Rational.compareTo(type) == 0) && (Type.Rational.compareTo(token.type) == 0)) {
//            return ((Rational) this).div((Rational) token);
//        }
//
//        if ((Type.Matrix.compareTo(type) == 0) && ((Type.Rational).compareTo(token.type) == 0)){
//            return ((Matrix) this).div((Rational) token);
//        }
//        throw new IllegalArgumentException("invalid operands in div");
//    }
//
//    public Token pow(Token token) throws IllegalArgumentException{
//        if ((Type.Matrix.compareTo(token.type) == 0) && (Type.Rational.compareTo(token.type) == 0)){
//            if (((Rational) token).getDenominator() != 1){
//                throw new IllegalArgumentException("invalid power in pow");
//            }
//            return ((Matrix) this).pow((Rational) token);
//        }
//        if ((Type.Rational.compareTo(type) == 0) && (Type.Rational.compareTo(token.type) == 0)){
//            return ((Rational) this).pow((Rational)token);
//        }
//        throw new IllegalArgumentException("invalid argument in pow");
//
//    }
//
//
//    // unary
//    public Token getInverseMatrix(){
//        if (this.type != Token.Type.Matrix) throw new IllegalArgumentException("wrong argument in inverse(A)");
//        return ((Matrix) this).getInverseMatrix();
//    }
//
//    public Token getDeterminant(){
//        if (this.type != Token.Type.Matrix) throw new IllegalArgumentException("wrong argument in det(A)");
//        return ((Matrix) this).getDeterminantByTriangleMatrix();
//    }
//
//    public Token getTransposeMatrix(){
//        if (this.type != Token.Type.Matrix) throw new IllegalArgumentException("wrong argument in trans(A)");
//        return ((Matrix) this).getTransposeMatrix();
//    }
//
//    public Token getTriangleMatrix(){
//        if (this.type != Token.Type.Matrix) throw new IllegalArgumentException("wrong argument in triangle(A)");
//        return ((Matrix) this).getTriangleMatrix();
//    }
//
//    public Token getRankOfMatrix(){
//        if (this.type != Token.Type.Matrix) throw new IllegalArgumentException("wrong argument in rank(A)");
//        return ((Matrix) this).getRank();
//    }
