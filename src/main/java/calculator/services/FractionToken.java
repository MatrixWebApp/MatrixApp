package calculator.services;

import calculator.Token;
import fraction.Fraction;

public class FractionToken extends Token {
    private Fraction fraction;

    public Fraction getFraction() {
        return fraction;
    }

    public void setFraction(Fraction fraction) {
        this.fraction = fraction;
    }

    public FractionToken(Fraction fraction){
        this.fraction = fraction;
        this.type = Type.Fraction;
    }

    public FractionToken(Integer numerator, Integer denominator){
        this.fraction = new Fraction(numerator, denominator);
        this.type = Type.Fraction;
    }

    public FractionToken(Integer numerator){
        this.fraction = new Fraction(numerator);
        this.type = Type.Fraction;
    }

    public FractionToken(Token token){
        this.fraction = ((FractionToken) token).fraction;
        this.type = Type.Fraction;
    }


    public Token mlp(Token token) throws IllegalArgumentException{
        // right operand is fraction
        if (Type.Fraction.compareTo(token.type) == 0) {
            return new FractionToken(
                    this.fraction.mlp(((FractionToken) token).getFraction())
            );
        }
        // right operand is matrix
        if (Type.Matrix.compareTo(token.type) == 0){
            return token.mlp(this);

        }

        throw new IllegalArgumentException("invalid operands in mlp");
    }

    public Token add(Token token) throws IllegalArgumentException{
        // right operand is fraction
        if (Type.Fraction.compareTo(token.type) == 0) {
            return new FractionToken(
                    this.fraction.add(((FractionToken) token).getFraction())
            );
        }
        throw new IllegalArgumentException("invalid operands in mlp");
    }

    public Token sub(Token token) throws IllegalArgumentException{
        // right operand is fraction
        if (Type.Fraction.compareTo(token.type) == 0) {
            return new FractionToken(
                    this.fraction.sub(((FractionToken) token).getFraction())
            );
        }
        throw new IllegalArgumentException("invalid operands in mlp");
    }

    public Token div(Token token) throws IllegalArgumentException{
        // right operand is fraction
        if (Type.Fraction.compareTo(token.type) == 0) {
            return new FractionToken(
                    this.fraction.div(((FractionToken) token).getFraction())
            );
        }
        throw new IllegalArgumentException("invalid operands in div");
    }

    public Token pow(Token token) throws IllegalArgumentException{
        // right operand is fraction
        if (Type.Fraction.compareTo(token.type) == 0) {
            return new FractionToken(
                    this.fraction.pow(((FractionToken) token).getFraction())
            );
        }
        throw new IllegalArgumentException("invalid operands in mlp");
    }

}
