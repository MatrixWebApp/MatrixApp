package calculator.services;

import calculator.Token;
import matrix.Matrix;
import rational.Rational;

public class MatrixToken extends Token {

    private Matrix matrix;

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public MatrixToken(Matrix matrix) {
        this.matrix = new Matrix(matrix);
        this.type = Type.Matrix;

    }

    public Token add(Token token) throws IllegalArgumentException {
        if (Type.Matrix.compareTo(token.type) == 0) {
            return new MatrixToken(
                    matrix.add(((MatrixToken) token).matrix)
            );
        }

        // I think it's bad idea to add Matrix and rational
//        if (Type.Rational.compareTo(token.type) == 0){
//            return new MatrixToken(
//                    matrix.add(((RationalToken) token).rational)
//            );
//        }

        throw new IllegalArgumentException("invalid operands in add");
    }

    public Token sub(Token token) throws IllegalArgumentException {
        if (Type.Matrix.compareTo(token.type) == 0) {
            return new MatrixToken(
                    matrix.sub(((MatrixToken) token).matrix)
            );
        }
        // I think it's bad idea to sub Matrix and rational
//        if ((type.compareTo(Type.Rational) == 0) && (token.type.compareTo(Type.Rational) == 0)) {
//            return ((Rational) this).sub((Rational) token);
//        }
        throw new IllegalArgumentException("invalid operands in sub");
    }


    public Token mlp(Token token) throws IllegalArgumentException{
        // token is matrix
        if (Type.Matrix.compareTo(token.type) == 0) {
            return new MatrixToken(
                    matrix.mlp(((MatrixToken) token).matrix)
            );
        }
        // token is rational
        if (Type.Rational.compareTo(token.type) == 0){
            return new MatrixToken(
                    matrix.mlp(((RationalToken) token).getRational())
            );
        }

        throw new IllegalArgumentException("invalid operands in mlp");
    }

    public Token pow(Token token) throws IllegalArgumentException {
        if (Type.Rational.compareTo(token.type) == 0) {
            if (((RationalToken) token).getRational().getDenominator() != 1) {
                throw new IllegalArgumentException("invalid power in pow");
            }
            return new MatrixToken(
                    matrix.pow(((RationalToken) token).getRational())
            );
        }
        throw new IllegalArgumentException("invalid argument in pow");
    }

        // unary
    public Token getInverseMatrix(){
        return new MatrixToken(
                matrix.getInverseMatrix()
        );
    }

    public Token getDeterminant(){
        if (this.type != Token.Type.Matrix) throw new IllegalArgumentException("wrong argument in det(A)");
        return new RationalToken(
                matrix.getDeterminantByTriangleMatrix()
        );
    }

    public Token getTransposeMatrix(){
        return new MatrixToken(
                matrix.getTransposeMatrix()
        );
    }

    public Token getTriangleMatrix(){
        return new MatrixToken(
                matrix.getTriangleMatrix()
        );
    }

    public Token getRankOfMatrix(){
        return new RationalToken(
                matrix.getRank()
        );
    }

}
