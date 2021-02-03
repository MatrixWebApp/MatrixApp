package rational;

import rational.RationalOperators;

import java.util.Objects;

import static java.lang.StrictMath.abs;

public class Rational implements RationalOperators {
    private int numerator;
    private int denominator;



    // конструкторы
    public Rational(){
        setNumerator(0);
        setDenominator(1);
    }

    public Rational(int number){
        setNumerator(number);
        setDenominator(1);
    }

    public Rational(int numerator, int denominator) throws IllegalArgumentException{
        if (denominator == 0){
            throw new IllegalArgumentException("denominator equals 0");
        }
        setNumerator(numerator);
        setDenominator(denominator);
        this.setRational(getReductionFraction(this));
    }

    public Rational(Rational rational){
        this.setRational(rational);
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

    public void setRational(Rational rational){
        this.setNumerator(rational.getNumerator());
        this.setDenominator(rational.getDenominator());
    }
    public String toString(){
        return "("+getNumerator()+"/"+getDenominator()+")";
    }

    public static void swap(Rational a, Rational b){
        Rational tmp = new Rational(a);

        a.setNumerator(b.numerator);
        a.setDenominator(b.denominator);

        b.setNumerator(tmp.numerator);
        b.setDenominator(tmp.denominator);

    }
    // операторы
    @Override
    public Rational add(Rational rational) {
        int lcm = lсm(this.getDenominator(), rational.getDenominator());
        return new Rational(getReductionFraction(new Rational(lcm/this.getDenominator()*this.getNumerator() + lcm/rational.getDenominator()*rational.getNumerator(), lcm)));
    }


    @Override
    public Rational sub(Rational rational) {
        return new Rational(this.add(rational.neg()));
    }

    @Override
    public Rational neg() {
        return new Rational(this.mlp(new Rational(-1)));
    }

    @Override
    public Rational mlp(Rational rational) {
        return new Rational(this.getNumerator()*rational.getNumerator(),
                            this.getDenominator()*rational.getDenominator());
    }

    @Override
    public Rational pow(Rational rational) {

        if (rational.getNumerator() < 0) {
            return new Rational(1).
                        div(new Rational((int) Math.pow(getNumerator(), abs(rational.numerator)), (int) Math.pow(getDenominator(), abs(rational.numerator))));
        } else {
            return new Rational((int) Math.pow(getNumerator(), abs(rational.numerator)), (int) Math.pow(getDenominator(), abs(rational.numerator)));
        }
    }

    @Override
    public Rational div(Rational rational) throws IllegalArgumentException{
        if (rational.equals(new Rational(0))){
            throw new IllegalArgumentException("/ by zero");
        }
        return new Rational(this.getNumerator()*rational.getDenominator(),
                            this.getDenominator()*rational.getNumerator());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rational rational = (Rational) o;
        return numerator == rational.numerator &&
                denominator == rational.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public boolean isNotEqual(Rational rational) {
        return !this.equals(rational);
    }

    @Override
    public int compareTo(Rational rational) {
        if ((this.sub(rational)).getNumerator() > 0) {
            return 1;
        }
        if ((this.sub(rational)).getNumerator() < 0) {
            return -1;
        }
        else return 0;
    }

    public static int lсm(int a, int b){
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

    static Rational getReductionFraction(Rational rational){
        int lcm = gcd(rational.getNumerator(), rational.getDenominator());
        rational.setNumerator(rational.getNumerator()/lcm);
        rational.setDenominator(rational.getDenominator()/lcm);
        if (rational.getNumerator() == 0){
            rational.setDenominator(1);
        }
        if (rational.getDenominator() < 0){
            rational.setDenominator(-rational.getDenominator());
            rational.setNumerator(-rational.getNumerator());
        }
        return rational;
    }
}
