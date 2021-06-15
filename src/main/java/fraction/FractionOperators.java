package fraction;


public interface FractionOperators {
    public Fraction add(Fraction fraction);
    public Fraction sub(Fraction fraction);
    public Fraction mlp(Fraction fraction);
    public Fraction div(Fraction fraction);
    public Fraction neg();
    public Fraction pow(Fraction fraction);


    public boolean isNotEqual(Fraction fraction);
    public int compareTo(Fraction fraction);

}
