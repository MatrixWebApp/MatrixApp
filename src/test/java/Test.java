import calculator.Calculator;
import calculator.Token;
import calculator.services.MatrixToken;
import calculator.services.Variable;
import matrix.Matrix;
import rational.Rational;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        ArrayList<ArrayList<Rational>> arrayList = new ArrayList(
                Arrays.asList(
                new ArrayList(Arrays.asList(new Rational(3), new Rational(32))),
                new ArrayList(Arrays.asList(new Rational(12), new Rational(-5)))
                )
        );

        Matrix matrix2 = new Matrix(arrayList);
        Matrix matrix = new Matrix(2,2);
        Variable var1 = new Variable("A", Token.Type.Matrix, new MatrixToken(matrix));
        Variable var2 = new Variable("B", Token.Type.Matrix, new MatrixToken(matrix2));

        Calculator calculator = new Calculator(new ArrayList<>(Arrays.asList(var1, var2)));
        System.out.println(calculator.evaluate("A+B").toString());


    }
}
