


import calculator.Calculator;
import calculator.Token;
import calculator.services.MatrixToken;
import calculator.services.RationalToken;
import calculator.services.Variable;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import gsonParser.GsonParser;
import gsonParser.GsonVariableParser;
import matrix.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rational.Rational;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


class
CalculatorTest {

    private Path path;
    private JsonElement testJson;

    @BeforeEach
    public void setup() {
        // ./src/main/resources/matrixTest
        path = Paths.get("..", "main", "resources", "test", "calculator.json");
        testJson = getJson(path);

    }

    private JsonElement getJson(Path fileName) {
        try {
            String jsonString = new String(
                    Files.readAllBytes(Paths.get(fileName.toString())));
            return new JsonParser().parse(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // parse array
    private ArrayList<Variable> getVariableArray(JsonElement json) {
        ArrayList<Variable> variables = new ArrayList<>();
        int quantity = json.getAsJsonObject().get("in").getAsJsonArray().size();
        for (int i = 0; i < quantity; i++) {
            Variable var = GsonParser.variableFromJson(
                    json.getAsJsonObject().get("in").getAsJsonArray().get(i).getAsJsonObject()
            );
            variables.add(var);
        }
        return variables;
    }


    @Test
    void evaluate1() {
        JsonElement json = testJson.getAsJsonObject().get("evaluate1");
        ArrayList<Variable> variables = getVariableArray(json);
        JsonElement out = json.getAsJsonObject().get("out");

        Calculator calculator = new Calculator(variables);

        Token actual = calculator.evaluate("A-B");
        Token expected = new MatrixToken(GsonParser.matrixFromJson(out));

        assertEquals(expected, actual);

    }

    @Test
    void evaluate2() {
        JsonElement json = testJson.getAsJsonObject().get("evaluate2");
        ArrayList<Variable> variables = getVariableArray(json);
        JsonElement out = json.getAsJsonObject().get("out");

        Calculator calculator = new Calculator(variables);

        Token actual = calculator.evaluate("A*(inverse(B)*C)");
        Token expected = new MatrixToken(GsonParser.matrixFromJson(out));
        assertEquals(expected, actual);
    }

    @Test
    void evaluate3() {
        JsonElement json = testJson.getAsJsonObject().get("evaluate3");
        ArrayList<Variable> variables = getVariableArray(json);
        JsonElement out = json.getAsJsonObject().get("out");

        Calculator calculator = new Calculator(variables);

        Token actual = calculator.evaluate("inverse(A)^2");
        Token expected = new MatrixToken(GsonParser.matrixFromJson(out));
        assertEquals(expected, actual);
        assertEquals(expected, actual);
    }

    @Test
    void evaluate4() {

        JsonElement json = testJson.getAsJsonObject().get("evaluate4");
        ArrayList<Variable> variables = getVariableArray(json);

        Calculator calculator = new Calculator(variables);

        Token actual = calculator.evaluate("det(A*10-9*A)+1854*(-1)^4434");
        Token expected = new RationalToken(0);

        assertEquals(expected, actual);
    }

    @Test
    void evaluate5() {

        JsonElement json = testJson.getAsJsonObject().get("evaluate5");
        ArrayList<Variable> variables = getVariableArray(json);
        JsonElement out = json.getAsJsonObject().get("out");

        Calculator calculator = new Calculator(variables);

        Token actual = calculator.evaluate("trans(A)");
        Token expected = new MatrixToken(GsonParser.matrixFromJson(out));

        assertEquals(expected, actual);
    }

    @Test
    void evaluate6() {

        JsonElement json = testJson.getAsJsonObject().get("evaluate6");
        ArrayList<Variable> variables = getVariableArray(json);
        JsonElement out = json.getAsJsonObject().get("out");

        Calculator calculator = new Calculator(variables);

        Token actual = calculator.evaluate("triangle(A)");
        Token expected = new MatrixToken(GsonParser.matrixFromJson(out));

        assertEquals(expected, actual);

    }

    @Test
    void evaluate7() {
        JsonElement json = testJson.getAsJsonObject().get("evaluate7");
        ArrayList<Variable> variables = getVariableArray(json);

        Calculator calculator = new Calculator(variables);

        Token actual = calculator.evaluate("rank(A*10)");

        Token expected = new RationalToken(new Rational(3));

        assertEquals(expected, actual);
    }

    @Test
    void evaluate8() {

        Calculator calculator = new Calculator(new ArrayList<>());

        Token actual = calculator.evaluate("(5/4)^(-8)");
        Token expected = new RationalToken(new Rational(65536, 390625));

        assertEquals(actual, expected);

    }

    @Test
    void exception1() {

        Calculator calculator = new Calculator(new ArrayList<>());
        calculator.evaluate("323*43/23");

        String actual = calculator.status;
        String expected = "OK";

        assertEquals(expected, actual);


    }

    @Test
    void exception2() {

        Calculator calculator = new Calculator(new ArrayList<>());
        calculator.evaluate("rank(A)");

        String actual = calculator.status;
        String expected = "wrong input";

        assertEquals(expected, actual);
    }

    @Test
    void exception3() {

        Calculator calculator = new Calculator(new ArrayList<>());
        calculator.evaluate("rank(0/0)");

        String actual = calculator.status;
        String expected = "/ by zero";

        assertEquals(expected, actual);
    }

    @Test
    void exception4() {

        Calculator calculator = new Calculator(new ArrayList<>());
        calculator.evaluate("454+)");

        String actual = calculator.status;
        String expected = "wrong input";

        assertEquals(expected, actual);
    }

    @Test
    void exception5() {

        Calculator calculator = new Calculator(new ArrayList<>());
        calculator.evaluate("454+");

        String actual = calculator.status;
        String expected = "wrong input";

        assertEquals(expected, actual);
    }

    @Test
    void exception6() {

        Calculator calculator = new Calculator(new ArrayList<>());
        calculator.evaluate("454+(");

        String actual = calculator.status;
        String expected = "wrong input";

        assertEquals(expected, actual);
    }

    @Test
    void exception7() {

        Calculator calculator = new Calculator(new ArrayList<>());
        calculator.evaluate("454+3(");

        String actual = calculator.status;
        String expected = "wrong input";

        assertEquals(expected, actual);
    }

    @Test
    void exception8() {

        Calculator calculator = new Calculator(new ArrayList<>());
        System.out.println(calculator.evaluate("4^"));

        String actual = calculator.status;
        String expected = "wrong input";

        assertEquals(expected, actual);
    }

    @Test
    void exception9() {

        Calculator calculator = new Calculator(new ArrayList<>());
        System.out.println(calculator.evaluate("tra"));

        String actual = calculator.status;
        String expected = "wrong input";

        assertEquals(expected, actual);
    }

    @Test
    void exception10() {

        Calculator calculator = new Calculator(new ArrayList<>());
        System.out.println(calculator.evaluate("trans(34)"));

        String actual = calculator.status;
        String expected = "wrong argument in trans(A)";

        assertEquals(expected, actual);
    }

    @Test
    void exception11() {

        Calculator calculator = new Calculator(new ArrayList<>());
        System.out.println(calculator.evaluate("inverse(34)"));

        String actual = calculator.status;
        String expected = "wrong argument in inverse(A)";

        assertEquals(expected, actual);
    }

    @Test
    void exception12() {

        Calculator calculator = new Calculator(new ArrayList<>());
        calculator.evaluate("triangle(432343423)");

        String actual = calculator.status;
        String expected = "wrong argument in triangle(A)";

        assertEquals(expected, actual);
    }
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
}