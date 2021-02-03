package rational;


public interface RationalOperators {
    public Rational add(Rational rational);
    public Rational sub(Rational rational);
    public Rational mlp(Rational rational);
    public Rational div(Rational rational);
    public Rational neg();
    public Rational pow(Rational rational);


    public boolean isNotEqual(Rational rational);
    public int compareTo(Rational rational);

}
