//package calculator;
//
//import calculator.view.gsonParser.GsonVariableParser;
//import model.Matrix.Matrix;
//import model.Rational.Rational;
//import org.junit.jupiter.api.Test;
//import view.Variable;
//import view.gsonParser.GsonParser;
//import view.gsonParser.GsonVariableParser;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//class
//CalculatorTest {
//
//
//    private final String pathToTest = "web"+File.separator+"resources"+File.separator+"calculatorTest";
//
//    private String getJsonStringIn(String fileName){
//        try {
//            return new String(Files.readAllBytes(Paths.get(pathToTest+File.separator+"in"+File.separator+fileName+"In.json")));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    private String getJsonStringOut(String fileName){
//        try {
//            return new String(Files.readAllBytes(Paths.get(pathToTest+File.separator+"Out"+File.separator+fileName+"Out.json")));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    private String getMethodName(int number){
//        return (new Throwable()).getStackTrace()[number].getMethodName();
//    }
//    private ArrayList<Variable> getInVariables(){
//        ArrayList<Variable> values = new ArrayList<>();
//        try {
//            int fileCount = Files.list(Paths.get(pathToTest + "\\in\\"+getMethodName(2))).collect(Collectors.toList()).size();
//            for (int i = 1; i <= fileCount; i++) {
//                values.add(GsonVariableParser.getVariable(getJsonStringIn(getMethodName(2)+"\\"+getMethodName(2)+"."+i)));
//            }
//        } catch(IOException e){
//            e.printStackTrace();
//        }
//        return values;
//    }
//
//
//
//    @Test
//    void evaluate1() {
//
//        ArrayList<Variable> variables = getInVariables();
//
//        Calculator calculator = new Calculator(variables);
//
//        Matrix actual = (Matrix) calculator.evaluate("A-B");
//        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName(1)));
//
//        assertEquals(expected, actual);
//
//    }
//
//    @Test
//    void evaluate2() {
//
//        ArrayList<Variable> variables = getInVariables();
//
//
//        Calculator calculator = new Calculator(variables);
//
//        Matrix actual = (Matrix) calculator.evaluate("A*(inverse(B)*C)");
//        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName(1)));
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void evaluate3() {
//
//        ArrayList<Variable> variables = getInVariables();
//
//        Calculator calculator = new Calculator(variables);
//
//        Matrix actual = (Matrix) calculator.evaluate("inverse(A)^2");
//        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName(1)));
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void evaluate4() {
//
//        ArrayList<Variable> variables = getInVariables();
//
//
//        Calculator calculator = new Calculator(variables);
//
//        Rational actual = (Rational) calculator.evaluate("det(A*10-9*A)-1854*(-1)^4434");
//        Rational expected = new Rational(0);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void evaluate5() {
//
//        ArrayList<Variable> variables = getInVariables();
//
//
//        Calculator calculator = new Calculator(variables);
//
//        Matrix actual = (Matrix) calculator.evaluate("trans(A)");
//        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName(1)));
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void evaluate6() {
//
//        ArrayList<Variable> variables = getInVariables();
//
//
//        Calculator calculator = new Calculator(variables);
//
//        Matrix actual = (Matrix) calculator.evaluate("triangle(A)");
//
//        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName(1)));
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void evaluate7() {
//
//        ArrayList<Variable> variables = getInVariables();
//
//
//        Calculator calculator = new Calculator(variables);
//
//        Rational actual = (Rational) calculator.evaluate("rank(A*10)");
//
//        Rational expected = new Rational(3);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void evaluate8() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//
//        Rational actual = (Rational) calculator.evaluate("(5/4)^(-8)");
//        Rational expected = new Rational( 65536, 390625);
//
//        assertEquals(actual, expected);
//
//    }
//
//    @Test
//    void exception1() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        calculator.evaluate("323*43/23");
//
//        String actual = calculator.status;
//        String expected = "OK";
//
//        assertEquals(expected, actual);
//
//
//    }
//
//    @Test
//    void exception2() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        calculator.evaluate("rank(A)");
//
//        String actual = calculator.status;
//        String expected = "wrong input";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception3() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        calculator.evaluate("rank(0/0)");
//
//        String actual = calculator.status;
//        String expected = "/ by zero";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception4() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        calculator.evaluate("454+)");
//
//        String actual = calculator.status;
//        String expected = "wrong input";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception5() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        calculator.evaluate("454+");
//
//        String actual = calculator.status;
//        String expected = "wrong input";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception6() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        calculator.evaluate("454+(");
//
//        String actual = calculator.status;
//        String expected = "wrong input";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception7() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        calculator.evaluate("454+3(");
//
//        String actual = calculator.status;
//        String expected = "wrong input";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception8() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        System.out.println(calculator.evaluate("4^"));
//
//        String actual = calculator.status;
//        String expected = "wrong input";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception9() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        System.out.println(calculator.evaluate("tra"));
//
//        String actual = calculator.status;
//        String expected = "wrong input";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception10() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        System.out.println(calculator.evaluate("trans(34)"));
//
//        String actual = calculator.status;
//        String expected = "wrong argument in trans(A)";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception11() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        System.out.println(calculator.evaluate("inverse(34)"));
//
//        String actual = calculator.status;
//        String expected = "wrong argument in inverse(A)";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception12() {
//
//        Calculator calculator = new Calculator(new ArrayList<>());
//        calculator.evaluate("triangle(432343423)");
//
//        String actual = calculator.status;
//        String expected = "wrong argument in triangle(A)";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception13() {
//
//        ArrayList<Variable> variables = getInVariables();
//
//        Calculator calculator = new Calculator(variables);
//        calculator.evaluate("rank(det(inverse(A^2)))");
//
//        String actual = calculator.status;
//        String expected = "wrong argument in rank(A)";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception14() {
//
//        ArrayList<Variable> variables = getInVariables();
//        Calculator calculator = new Calculator(variables);
//        calculator.evaluate("4^A");
//
//        String actual = calculator.status;
//        String expected = "invalid argument in pow";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception15() {
//
//        ArrayList<Variable> variables = getInVariables();
//        Calculator calculator = new Calculator(variables);
//        calculator.evaluate("A^A");
//
//        String actual = calculator.status;
//        String expected = "invalid argument in pow";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception16() {
//
//        ArrayList<Variable> variables = getInVariables();
//        Calculator calculator = new Calculator(variables);
//        calculator.evaluate("5-A");
//
//        String actual = calculator.status;
//        String expected = "invalid operands in sub";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception17() {
//
//        ArrayList<Variable> variables = getInVariables();
//        Calculator calculator = new Calculator(variables);
//        calculator.evaluate("5+A");
//
//        String actual = calculator.status;
//        String expected = "invalid operands in add";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception18() {
//
//        ArrayList<Variable> variables = getInVariables();
//        Calculator calculator = new Calculator(variables);
//        calculator.evaluate("5*A");
//
//        String actual = calculator.status;
//        String expected = "invalid operands in mlp";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception19() {
//
//        ArrayList<Variable> variables = getInVariables();
//        Calculator calculator = new Calculator(variables);
//        calculator.evaluate("5/A");
//
//        String actual = calculator.status;
//        String expected = "invalid operands in div";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void exception20() {
//
//        ArrayList<Variable> variables = getInVariables();
//        Calculator calculator = new Calculator(variables);
//        calculator.evaluate("A/A");
//
//        String actual = calculator.status;
//        String expected = "invalid operands in div";
//
//        assertEquals(expected, actual);
//    }
//}