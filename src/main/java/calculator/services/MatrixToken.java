package calculator.services;

import calculator.Token;
import matrix.Matrix;
import fraction.Fraction;

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

        // I think it's bad idea to add Matrix and fraction
//        if (Type.Fraction.compareTo(token.type) == 0){
//            return new MatrixToken(
//                    matrix.add(((FractionToken) token).fraction)
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
        // I think it's bad idea to sub Matrix and fraction
//        if ((type.compareTo(Type.Fraction) == 0) && (token.type.compareTo(Type.Fraction) == 0)) {
//            return ((Fraction) this).sub((Fraction) token);
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
        // token is fraction
        if (Type.Fraction.compareTo(token.type) == 0){
            return new MatrixToken(
                    matrix.mlp(((FractionToken) token).getFraction())
            );
        }

        throw new IllegalArgumentException("invalid operands in mlp");
    }

    public Token pow(Token token) throws IllegalArgumentException {
        if (Type.Fraction.compareTo(token.type) == 0) {
            if (((FractionToken) token).getFraction().getDenominator() != 1) {
                throw new IllegalArgumentException("invalid power in pow");
            }
            return new MatrixToken(
                    matrix.pow(((FractionToken) token).getFraction())
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
        return new FractionToken(
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
        return new FractionToken(
                matrix.getRank()
        );
    }

}
