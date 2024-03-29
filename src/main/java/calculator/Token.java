package calculator;


import calculator.services.MatrixToken;
import calculator.services.FractionToken;
import matrix.Matrix;

import java.util.Objects;

public class Token{
    public Type type;

    public enum Type{
        Matrix,
        Fraction
    }



    public Token add(Token token) throws IllegalArgumentException{
        return add(token);
    }

    public Token sub(Token token) throws IllegalArgumentException {
        return sub(token);
    }


    public Token mlp(Token token) throws IllegalArgumentException {
        return mlp(token);
    }

    public Token div(Token token) throws IllegalArgumentException {
        return div(token);
    }

    public Token pow(Token token) throws IllegalArgumentException {
        return pow(token);
    }

    public Token getInverseMatrix() {
        return getInverseMatrix();
    }

    public Token getDeterminant() {
        return getDeterminant();
    }

    public Token getTransposeMatrix() {
        return getTransposeMatrix();
    }

    public Token getTriangleMatrix() {
        return getTransposeMatrix();
    }

    public Token getRankOfMatrix() {
        return getRankOfMatrix();
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
            case Fraction:
                return ((FractionToken) this).getFraction().equals(((FractionToken) token).getFraction());
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
//        if ((Type.Fraction.compareTo(type) == 0) && (Type.Fraction.compareTo(token.type) == 0)) {
//            return ((Fraction) this).add((Fraction) token);
//        }
//
//        if ((Type.Matrix.compareTo(type) == 0) && ((Type.Fraction).compareTo(token.type) == 0)){
//            return ((Matrix) this).add((Fraction) token);
//        }
//
//        throw new IllegalArgumentException("invalid operands in add");
    }

//    public Token sub(Token token) throws IllegalArgumentException{
//        if ((Type.Matrix.compareTo(this.type) == 0) && (Type.Matrix.compareTo(token.type) == 0)) {
//            return ((Matrix) this).sub((Matrix) token);
//        }
//        if ((type.compareTo(Type.Fraction) == 0) && (token.type.compareTo(Type.Fraction) == 0)) {
//            return ((Fraction) this).sub((Fraction) token);
//        }
//        throw new IllegalArgumentException("invalid operands in sub");
//    }
//
//    public Token mlp(Token token) throws IllegalArgumentException{
//        if ((Type.Matrix.compareTo(type) == 0) && (Type.Matrix.compareTo(token.type) == 0)) {
//            return ((Matrix) this).mlp((Matrix) token);
//        }
//        if ((Type.Fraction.compareTo(type) == 0) && (Type.Fraction.compareTo(token.type) == 0)) {
//            return ((Fraction) this).mlp((Fraction) token);
//        }
//        if ((Type.Matrix.compareTo(type) == 0) && ((Type.Fraction).compareTo(token.type) == 0)){
//            return ((Matrix) this).mlp((Fraction) token);
//        }
//
//        throw new IllegalArgumentException("invalid operands in mlp");
//    }
//
//    public Token div(Token token) throws IllegalArgumentException{
//        if ((Type.Matrix.compareTo(type) == 0) && (Type.Matrix.compareTo(token.type) == 0)) {
//            return ((Matrix) this).div((Matrix) token);
//        }
//        if ((Type.Fraction.compareTo(type) == 0) && (Type.Fraction.compareTo(token.type) == 0)) {
//            return ((Fraction) this).div((Fraction) token);
//        }
//
//        if ((Type.Matrix.compareTo(type) == 0) && ((Type.Fraction).compareTo(token.type) == 0)){
//            return ((Matrix) this).div((Fraction) token);
//        }
//        throw new IllegalArgumentException("invalid operands in div");
//    }
//
//    public Token pow(Token token) throws IllegalArgumentException{
//        if ((Type.Matrix.compareTo(token.type) == 0) && (Type.Fraction.compareTo(token.type) == 0)){
//            if (((Fraction) token).getDenominator() != 1){
//                throw new IllegalArgumentException("invalid power in pow");
//            }
//            return ((Matrix) this).pow((Fraction) token);
//        }
//        if ((Type.Fraction.compareTo(type) == 0) && (Type.Fraction.compareTo(token.type) == 0)){
//            return ((Fraction) this).pow((Fraction)token);
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
