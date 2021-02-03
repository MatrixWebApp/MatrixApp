package matrix;


import rational.Rational;

public interface MatrixOperators {
    public Matrix add(Matrix matrix);
    public Matrix add(Rational rational);
    public Matrix sub(Matrix matrix);
    public Matrix sub(Rational rational);
    public Matrix mlp(Matrix matrix);
    public Matrix mlp(Rational rational);
    public Matrix div(Matrix matrix);
    public Matrix div(Rational rational);
    public Matrix pow(Rational rational);
    public Matrix getTransposeMatrix();
    public Matrix getInverseMatrix();
    public Matrix toIMatrix();
    public Matrix neg();

}
