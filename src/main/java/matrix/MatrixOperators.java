package matrix;


import fraction.Fraction;

public interface MatrixOperators {
    public Matrix add(Matrix matrix);
    public Matrix add(Fraction fraction);
    public Matrix sub(Matrix matrix);
    public Matrix sub(Fraction fraction);
    public Matrix mlp(Matrix matrix);
    public Matrix mlp(Fraction fraction);
    public Matrix div(Matrix matrix);
    public Matrix div(Fraction fraction);
    public Matrix pow(Fraction fraction);
    public Matrix getTransposeMatrix();
    public Matrix getInverseMatrix();
    public Matrix toIMatrix();
    public Matrix neg();

}
