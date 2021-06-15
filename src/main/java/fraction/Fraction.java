package fraction;

import java.util.Objects;

import static java.lang.StrictMath.abs;

public class Fraction implements FractionOperators {
    private int numerator;
    private int denominator;



    // конструкторы
    public Fraction(){
        setNumerator(0);
        setDenominator(1);
    }

    public Fraction(int number){
        setNumerator(number);
        setDenominator(1);
    }

    public Fraction(int numerator, int denominator) throws IllegalArgumentException{
        if (denominator == 0){
            throw new IllegalArgumentException("denominator equals 0");
        }
        setNumerator(numerator);
        setDenominator(denominator);
        this.setFraction(getReductionFraction(this));
    }

    public Fraction(Fraction fraction){
        this.setFraction(fraction);
    }

    // Геттеры и сеттеры
    public int getNumerator() {
        return numerator;
    }

    private void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    private void setDenominator(int denominator) throws IllegalArgumentException {
        if (denominator == 0){
            throw new IllegalArgumentException("denominator equals 0");
        }
        this.denominator = denominator;
    }

    public void setFraction(Fraction fraction){
        this.setNumerator(fraction.getNumerator());
        this.setDenominator(fraction.getDenominator());
    }
    public String toString(){
        return "("+getNumerator()+"/"+getDenominator()+")";
    }

    public static void swap(Fraction a, Fraction b){
        Fraction tmp = new Fraction(a);

        a.setNumerator(b.numerator);
        a.setDenominator(b.denominator);

        b.setNumerator(tmp.numerator);
        b.setDenominator(tmp.denominator);

    }
    // операторы
    @Override
    public Fraction add(Fraction fraction) {
        int lcm = lcm(this.getDenominator(), fraction.getDenominator());
        return new Fraction(getReductionFraction(new Fraction(lcm/this.getDenominator()*this.getNumerator() + lcm/fraction.getDenominator()*fraction.getNumerator(), lcm)));
    }


    @Override
    public Fraction sub(Fraction fraction) {
        return new Fraction(this.add(fraction.neg()));
    }

    @Override
    public Fraction neg() {
        return new Fraction(this.mlp(new Fraction(-1)));
    }

    @Override
    public Fraction mlp(Fraction fraction) {
        return new Fraction(this.getNumerator()*fraction.getNumerator(),
                            this.getDenominator()*fraction.getDenominator());
    }

    @Override
    public Fraction pow(Fraction fraction) {

        if (fraction.getNumerator() < 0) {
            return new Fraction(1).
                        div(new Fraction((int) Math.pow(getNumerator(), abs(fraction.numerator)), (int) Math.pow(getDenominator(), abs(fraction.numerator))));
        } else {
            return new Fraction((int) Math.pow(getNumerator(), abs(fraction.numerator)), (int) Math.pow(getDenominator(), abs(fraction.numerator)));
        }
    }

    @Override
    public Fraction div(Fraction fraction) throws IllegalArgumentException{
        if (fraction.equals(new Fraction(0))){
            throw new IllegalArgumentException("/ by zero");
        }
        return new Fraction(this.getNumerator()*fraction.getDenominator(),
                            this.getDenominator()*fraction.getNumerator());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator &&
                denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public boolean isNotEqual(Fraction fraction) {
        return !this.equals(fraction);
    }

    @Override
    public int compareTo(Fraction fraction) {
        if ((this.sub(fraction)).getNumerator() > 0) {
            return 1;
        }
        if ((this.sub(fraction)).getNumerator() < 0) {
            return -1;
        }
        else return 0;
    }

    public static int lcm(int a, int b){
        if ((a == 0) || (b == 0)){
            throw new IllegalArgumentException("arguments equal 0");
        }
        a = abs(a);
        b = abs(b);
        return (a*b)/gcd(a,b);

    }

    static int gcd(int a, int b){
        if ((a == 0) && (b == 0)){
            throw new IllegalArgumentException("arguments equal 0");
        }
        a = abs(a);
        b = abs(b);
        int mlp = a*b;
        while ((a != 0) && (b != 0)){
            if (a > b){
                a%=b;
            }
            else{
                b%=a;
            }
        }
        return a+b;
    }

    static Fraction getReductionFraction(Fraction fraction){
        int lcm = gcd(fraction.getNumerator(), fraction.getDenominator());
        fraction.setNumerator(fraction.getNumerator()/lcm);
        fraction.setDenominator(fraction.getDenominator()/lcm);
        if (fraction.getNumerator() == 0){
            fraction.setDenominator(1);
        }
        if (fraction.getDenominator() < 0){
            fraction.setDenominator(-fraction.getDenominator());
            fraction.setNumerator(-fraction.getNumerator());
        }
        return fraction;
    }
}
