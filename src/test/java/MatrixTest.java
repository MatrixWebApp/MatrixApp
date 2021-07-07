import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import gsonParser.GsonParser;
import matrix.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fraction.Fraction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MatrixTest {

    private Path path;
    private JsonElement testJson;


    @BeforeEach
    public void setup(){
        // ./src/main/resources/matrixTest
        path = Paths.get( "src", "main", "resources","static", "test", "matrix.json");
        testJson = getJson(path);

    }

    private JsonElement getJson(Path fileName){
        try {
            String jsonString = new String(
                    Files.readAllBytes(Paths.get(fileName.toString())));
            return new JsonParser().parse(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void test1(){
        Matrix actual = new Matrix(3,3);
        Matrix expected = GsonParser.matrixFromJson(
                testJson.getAsJsonObject().get("constructor1")
        );
        assertEquals(expected, actual);
    }

    @Test
    public void test2(){
        Matrix actual = new Matrix(2,2);
        Matrix expected = GsonParser.matrixFromJson(
                testJson.getAsJsonObject().get("constructor2")
        );
        assertEquals(expected, actual);
    }

    @Test
    public void test3(){

        Matrix actual = new Matrix(1,1);
        Matrix expected = GsonParser.matrixFromJson(
                testJson.getAsJsonObject().get("constructor3")
        );
        assertEquals(expected, actual);
    }


    @Test
    public void test4(){
        JsonElement json =  testJson.getAsJsonObject().get("swipeColumn1");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in);
        Matrix expected = GsonParser.matrixFromJson(out);

        actual.swipeColumn(0,1);

        assertEquals(expected, actual);
    }

    @Test
    public void test5(){
        JsonElement json =  testJson.getAsJsonObject().get("swipeRow1");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in);
        Matrix expected = GsonParser.matrixFromJson(out);

        actual.swipeRow(0,1);


        assertEquals(expected, actual);
    }

    @Test
    public void test6(){

        JsonElement json =  testJson.getAsJsonObject().get("addRow1");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in);
        Matrix expected = GsonParser.matrixFromJson(out);

        actual.addRow(0,1, new Fraction(1,1));

        assertEquals(expected, actual);
    }


    @Test
    public void test7(){
        JsonElement json =  testJson.getAsJsonObject().get("mlpRow1");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in);
        Matrix expected = GsonParser.matrixFromJson(out);

        actual.mlpRow(1, new Fraction(1,3));

        assertEquals(expected, actual);
    }

    @Test
    public void test8() {
        JsonElement json = testJson.getAsJsonObject().get("determinantOfTriangleMatrix1");
        JsonElement in = json.getAsJsonObject().get("in");

        Fraction actual = GsonParser.matrixFromJson(in).getDeterminantOfTriangleMatrix();
        Fraction expected = new Fraction(45, 28);

        assertEquals(expected, actual);
    }

    @Test
    public void test9() {
        JsonElement json =  testJson.getAsJsonObject().get("getInverseMatrix1");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).getInverseMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }

    @Test
    public void test10() {
        JsonElement json =  testJson.getAsJsonObject().get("getInverseMatrix2");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).getInverseMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);

    }
//
    @Test
    public void test11() {
        JsonElement json =  testJson.getAsJsonObject().get("getInverseMatrix2");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).getInverseMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);

    }

//
    @Test
    public void test12() {
        JsonElement json =  testJson.getAsJsonObject().get("add1");
        List<JsonElement> in = Arrays.asList(
                json.getAsJsonObject().get("in").getAsJsonArray().get(0).getAsJsonObject(),
                json.getAsJsonObject().get("in").getAsJsonArray().get(1).getAsJsonObject()
        );
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser
                .matrixFromJson(in.get(0))
                .add(GsonParser.matrixFromJson(in.get(1)));
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }

    @Test
    public void test13() {
        JsonElement json =  testJson.getAsJsonObject().get("mlp1");
        List<JsonElement> in = Arrays.asList(
                json.getAsJsonObject().get("in").getAsJsonArray().get(0).getAsJsonObject(),
                json.getAsJsonObject().get("in").getAsJsonArray().get(1).getAsJsonObject()
        );
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser
                .matrixFromJson(in.get(0))
                .mlp(GsonParser.matrixFromJson(in.get(1)));
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);

    }

    @Test
    public void test14() {
        JsonElement json =  testJson.getAsJsonObject().get("mlp2");
        List<JsonElement> in = Arrays.asList(
                json.getAsJsonObject().get("in").getAsJsonArray().get(0).getAsJsonObject(),
                json.getAsJsonObject().get("in").getAsJsonArray().get(1).getAsJsonObject()
        );
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser
                .matrixFromJson(in.get(0))
                .mlp(GsonParser.matrixFromJson(in.get(1)));
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }



    @Test
    public void test15(){
        JsonElement json =  testJson.getAsJsonObject().get("toIMatrix1");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).toIMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }

    @Test
    public void test16(){
        JsonElement json =  testJson.getAsJsonObject().get("toIMatrix2");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).toIMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }


    @Test
    public void test17(){
        JsonElement json =  testJson.getAsJsonObject().get("toIMatrix3");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).toIMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }


    @Test
    public void test18(){
        JsonElement json =  testJson.getAsJsonObject().get("toIMatrix4");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).toIMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }


    @Test
    public void test19(){
        JsonElement json =  testJson.getAsJsonObject().get("toIMatrix5");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).toIMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }


    @Test
    public void test20(){
        JsonElement json =  testJson.getAsJsonObject().get("rank1");
        JsonElement in = json.getAsJsonObject().get("in");

        int actual = GsonParser.matrixFromJson(in).getRank().getNumerator();
        int expected = 2;

        assertEquals(expected, actual);
    }


    @Test
    public void test21(){
        JsonElement json =  testJson.getAsJsonObject().get("rank2");
        JsonElement in = json.getAsJsonObject().get("in");

        int actual = GsonParser.matrixFromJson(in).getRank().getNumerator();
        int expected = 4;

        assertEquals(expected, actual);
    }

    @Test
    public void test22(){
        JsonElement json =  testJson.getAsJsonObject().get("rank3");
        JsonElement in = json.getAsJsonObject().get("in");

        int actual = GsonParser.matrixFromJson(in).getRank().getNumerator();
        int expected = 3;

        assertEquals(expected, actual);
    }

    @Test
    public void test23(){
        JsonElement json =  testJson.getAsJsonObject().get("rank4");
        JsonElement in = json.getAsJsonObject().get("in");

        int actual = GsonParser.matrixFromJson(in).getRank().getNumerator();
        int expected = 3;

        assertEquals(expected, actual);
    }

    @Test
    public void test24(){
        JsonElement json =  testJson.getAsJsonObject().get("rank5");
        JsonElement in = json.getAsJsonObject().get("in");

        int actual = GsonParser.matrixFromJson(in).getRank().getNumerator();
        int expected = 3;

        assertEquals(expected, actual);
    }


    @Test
    public void test25(){
        JsonElement json =  testJson.getAsJsonObject().get("rank6");
        JsonElement in = json.getAsJsonObject().get("in");

        int actual = GsonParser.matrixFromJson(in).getRank().getNumerator();
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    public void test26(){
        JsonElement json =  testJson.getAsJsonObject().get("rank7");
        JsonElement in = json.getAsJsonObject().get("in");

        int actual = GsonParser.matrixFromJson(in).getRank().getNumerator();
        int expected = 3;

        assertEquals(expected, actual);
    }

    @Test
    public void test27(){
        JsonElement json =  testJson.getAsJsonObject().get("determinantByTriangleMatrix1");
        JsonElement in = json.getAsJsonObject().get("in");

        Fraction actual = GsonParser.matrixFromJson(in).getDeterminantByTriangleMatrix();
        Fraction expected = new Fraction(0);

        assertEquals(expected, actual);
    }

    @Test
    public void test28(){
        JsonElement json =  testJson.getAsJsonObject().get("determinantByTriangleMatrix2");
        JsonElement in = json.getAsJsonObject().get("in");

        Fraction actual = GsonParser.matrixFromJson(in).getDeterminantByTriangleMatrix();

        Fraction expected = new Fraction(-1034);
        assertEquals(expected, actual);
    }

    @Test
    public void test29(){
        JsonElement json =  testJson.getAsJsonObject().get("determinantByTriangleMatrix3");
        JsonElement in = json.getAsJsonObject().get("in");

        Fraction actual = GsonParser.matrixFromJson(in).getDeterminantByTriangleMatrix();
        Fraction expected = new Fraction(-42);

        assertEquals(expected, actual);
    }

    @Test
    public void test30(){
        JsonElement json =  testJson.getAsJsonObject().get("determinantByTriangleMatrix4");
        JsonElement in = json.getAsJsonObject().get("in");

        Fraction actual = GsonParser.matrixFromJson(in).getDeterminantByTriangleMatrix();
        Fraction expected = new Fraction(24);

        assertEquals(expected, actual);
    }


    @Test
    public void test31(){
        JsonElement json =  testJson.getAsJsonObject().get("determinantByTriangleMatrix5");
        JsonElement in = json.getAsJsonObject().get("in");

        Matrix matrix = GsonParser.matrixFromJson(in);
        assertThrows(IllegalArgumentException.class, ()-> matrix.getDeterminantByTriangleMatrix());
    }

    @Test
    public void test32(){
        JsonElement json =  testJson.getAsJsonObject().get("determinantByTriangleMatrix6");
        JsonElement in = json.getAsJsonObject().get("in");

        Fraction actual = GsonParser.matrixFromJson(in).getDeterminantByTriangleMatrix();
        Fraction expected = new Fraction(0);
        assertEquals(expected, actual);
    }

//    @Test
//    public void getSolutionOfLES1(){
//        JsonElement json =  testJson.getAsJsonObject().get("determinantByTriangleMatrix6");
//        JsonElement in = json.getAsJsonObject().get("in");
//
//        String actual = GsonParser.matrixFromJson(in).getSolutionOfLES().toString();
//        String expected = "Independent variables:\n" +
//                "X1 = (-3/2)\n" +
//                "X2 = (1/1)\n" +
//                "X3 = (1/2)\n" +
//                "\n" +
//                "Dependent variables:\n";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void getSolutionOfLES2(){
//
//        String actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName()))
//                                        .getSolutionOfLES()
//                                        .toString();
//        String exprected = "Independent variables:\n" +
//                "X1 = (9/4) + (-2/1)*X2 + (-5/4)*X5\n" +
//                "X3 = (3/4) + (-3/4)*X5\n" +
//                "X4 = (-1/2) + (-1/2)*X5\n" +
//                "\n" +
//                "Dependent variables:\n" +
//                "X2 = a1\n" +
//                "X5 = a2\n";
//        assertEquals(exprected, actual);
//
//    }



    @Test
    public void test33(){
        JsonElement json =  testJson.getAsJsonObject().get("transposeMatrix1");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).getTransposeMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }


    @Test
    public void test34(){
        JsonElement json =  testJson.getAsJsonObject().get("inverseMatrix1");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).getInverseMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }

    @Test
    public void test35(){
        JsonElement json =  testJson.getAsJsonObject().get("inverseMatrix2");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).getInverseMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }

    @Test
    public void test36(){
        JsonElement json =  testJson.getAsJsonObject().get("inverseMatrix3");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).getInverseMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }

    @Test
    public void test37(){
        JsonElement json =  testJson.getAsJsonObject().get("determinantByTriangleMatrix7");
        JsonElement in = json.getAsJsonObject().get("in");

        Fraction actual = GsonParser.matrixFromJson(in).getDeterminantByTriangleMatrix();
        Fraction expected = new Fraction(-1854);
        assertEquals(expected, actual);
    }



//    @Test
//    @Test
//    @Test
//    public void getDecompositionOfVectorsByLinearIndependent1(){
//        String actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getDecompositionOfVectorsByLinearIndependent();
//        String expected = "Basis:\n" +
//                "vector2\n" +
//                "vector4\n" +
//                "\n" +
//                "Decomposition by basis:\n" +
//                "vector1 = (0/1)*vector2 + (0/1)*vector4\n" +
//                "vector2 = (1/1)*vector2 + (0/1)*vector4\n" +
//                "vector3 = (-1/1)*vector2 + (0/1)*vector4\n" +
//                "vector4 = (0/1)*vector2 + (1/1)*vector4\n" +
//                "vector5 = (0/1)*vector2 + (2/1)*vector4\n";
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void getDecompositionOfVectorsByLinearIndependent2(){
//        String actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getDecompositionOfVectorsByLinearIndependent();
//        String expected = "Basis:\n" +
//                "vector1\n" +
//                "vector2\n" +
//                "vector4\n" +
//                "\n" +
//                "Decomposition by basis:\n" +
//                "vector1 = (1/1)*vector1 + (0/1)*vector2 + (0/1)*vector4\n" +
//                "vector2 = (0/1)*vector1 + (1/1)*vector2 + (0/1)*vector4\n" +
//                "vector3 = (0/1)*vector1 + (5645465/1)*vector2 + (0/1)*vector4\n" +
//                "vector4 = (0/1)*vector1 + (0/1)*vector2 + (1/1)*vector4\n" +
//                "vector5 = (-10/1)*vector1 + (7/1)*vector2 + (6/1)*vector4\n";
//        assertEquals(expected, actual);
//    }


}