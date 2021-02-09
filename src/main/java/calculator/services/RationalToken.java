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

    public RationalToken(Integer numerator){
        this.rational = new Rational(numerator);
        this.type = Type.Rational;
    }

    public RationalToken(Token token){
        this.rational = ((RationalToken) token).rational;
        this.type = Type.Rational;
    }


}
