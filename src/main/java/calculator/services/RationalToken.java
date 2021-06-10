package calculator.services;

import calculator.Token;
import rational.Rational;

public class RationalToken extends Token {
    private Rational rational;

    public Rational getRational() {
        return rational;
    }

    public void setRational(Rational rational) {
        this.rational = rational;
    }

    public RationalToken(Rational rational){
        this.rational = rational;
        this.type = Type.Rational;
    }

    public RationalToken(Integer numerator, Integer denominator){
        this.rational = new Rational(numerator, denominator);
        this.type = Type.Rational;
    }

    public RationalToken(Integer numerator){
        this.rational = new Rational(numerator);
        this.type = Type.Rational;
    }

    public RationalToken(Token token){
        this.rational = ((RationalToken) token).rational;
        this.type = Type.Rational;
    }


    public Token mlp(Token token) throws IllegalArgumentException{
        // right operand is rational
        if (Type.Rational.compareTo(token.type) == 0) {
            return new RationalToken(
                    this.rational.mlp(((RationalToken) token).getRational())
            );
        }
        // right operand is matrix
        if (Type.Matrix.compareTo(token.type) == 0){
            return token.mlp(this);

        }

        throw new IllegalArgumentException("invalid operands in mlp");
    }

    public Token add(Token token) throws IllegalArgumentException{
        // right operand is rational
        if (Type.Rational.compareTo(token.type) == 0) {
            return new RationalToken(
                    this.rational.add(((RationalToken) token).getRational())
            );
        }
        throw new IllegalArgumentException("invalid operands in mlp");
    }

    public Token sub(Token token) throws IllegalArgumentException{
        // right operand is rational
        if (Type.Rational.compareTo(token.type) == 0) {
            return new RationalToken(
                    this.rational.sub(((RationalToken) token).getRational())
            );
        }
        throw new IllegalArgumentException("invalid operands in mlp");
    }

    public Token div(Token token) throws IllegalArgumentException{
        // right operand is rational
        if (Type.Rational.compareTo(token.type) == 0) {
            return new RationalToken(
                    this.rational.div(((RationalToken) token).getRational())
            );
        }
        throw new IllegalArgumentException("invalid operands in div");
    }

    public Token pow(Token token) throws IllegalArgumentException{
        // right operand is rational
        if (Type.Rational.compareTo(token.type) == 0) {
            return new RationalToken(
                    this.rational.pow(((RationalToken) token).getRational())
            );
        }
        throw new IllegalArgumentException("invalid operands in mlp");
    }

}
