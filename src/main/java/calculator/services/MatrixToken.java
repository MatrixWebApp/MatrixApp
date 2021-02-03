package calculator.services;

import calculator.Token;
import calculator.Type;
import matrix.Matrix;
import rational.Rational;

public class MatrixToken implements Token{

    private Matrix matrix;

    @Override
    public Token add(Token token) throws IllegalArgumentException {
        if ((Type.Matrix.compareTo(type) == 0) && (Type.Matrix.compareTo(token.type) == 0)) {
            return ((Matrix) this).add((Matrix) token);
        }
        if ((Type.Rational.compareTo(type) == 0) && (Type.Rational.compareTo(token.type) == 0)) {
            return ((Rational) this).add((Rational) token);
        }

        if ((Type.Matrix.compareTo(type) == 0) && ((Type.Rational).compareTo(token.type) == 0)){
            return ((Matrix) this).add((Rational) token);
        }

        throw new IllegalArgumentException("invalid operands in add");
    }
}
