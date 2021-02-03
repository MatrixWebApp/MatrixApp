import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import gsonParser.GsonParser;
import matrix.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rational.Rational;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;


class MatrixTest {

    private Path path;
    private JsonElement testJson;


    @BeforeEach
    public void setup(){
        // ./src/main/resources/matrixTest
        path = Paths.get("." ,"src","main","resources","matrixTest", "test.json");
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

        actual.addRow(0,1, new Rational(1,1));

        assertEquals(expected, actual);
    }


    @Test
    public void test7(){
        JsonElement json =  testJson.getAsJsonObject().get("mlpRow1");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in);
        Matrix expected = GsonParser.matrixFromJson(out);

        actual.mlpRow(1, new Rational(1,3));

        assertEquals(expected, actual);
    }

    @Test
    public void determinantOfTriangleMatrix1() {
        JsonElement json = testJson.getAsJsonObject().get("determinantOfTriangleMatrix1");
        JsonElement in = json.getAsJsonObject().get("in");

        Rational actual = GsonParser.matrixFromJson(in).getDeterminantOfTriangleMatrix();
        Rational expected = new Rational(45, 28);

        assertEquals(expected, actual);
    }

    @Test
    public void getInverseMatrix1() {
        JsonElement json =  testJson.getAsJsonObject().get("getInverseMatrix1");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).getInverseMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);
    }

    @Test
    public void getInverseMatrix2() {
        JsonElement json =  testJson.getAsJsonObject().get("getInverseMatrix2");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).getInverseMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);

    }
//
    @Test
    public void getInverseMatrix3() {
        JsonElement json =  testJson.getAsJsonObject().get("getInverseMatrix2");
        JsonElement in = json.getAsJsonObject().get("in");
        JsonElement out = json.getAsJsonObject().get("out");

        Matrix actual = GsonParser.matrixFromJson(in).getInverseMatrix();
        Matrix expected = GsonParser.matrixFromJson(out);

        assertEquals(expected, actual);

    }

//
//    @Test
//    public void add1() {
//        Matrix actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName()+".1"))
//                                        .add(GsonParser.matrixFromJson(getJsonStringIn(getMethodName()+".2")));
//
//        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName()));
//
//        assertEquals(expected, actual);
//
//    }
//
//    @Test
//    public void mlp1() {
//        Matrix actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName()+".1"))
//                .mlp(GsonParser.matrixFromJson(getJsonStringIn(getMethodName()+".2")));
//
//        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName()));
//
//        assertEquals(expected, actual);
//    }
//
////
////    @Test
////    public void mlp2() {
////        Matrix actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName()+".1"))
////                                        .mlp(GsonParser.matrixFromJson(getJsonStringIn(getMethodName()+".2")));
////
////        System.out.println(actual.add(actual).toString());
////        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName()));
////
////        assertEquals(expected, actual);
////
////    }
////
////    @Test
////    public void mlp3() {
////        Matrix a = GsonParser.matrixFromJson(getJsonStringIn(getMethodName()+".1"));
////        Matrix b = GsonParser.matrixFromJson(getJsonStringIn(getMethodName()+".2"));
////
////        System.out.println(a);
////        System.out.println(a.toIMatrix());
////        System.out.println(a.getDecompositionOfVectorsByLinearIndependent());
////
////
////        //assertEquals(expected, actual);
////    }
////
////
////    @Test
////    public void toIMatrix1(){
////
////        Matrix actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).toIMatrix();
////        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName()));
////
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void toIMatrix2(){
////
////        Matrix actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).toIMatrix();
////        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName()));
////
////        assertEquals(expected, actual);
////    }
////
////
////    @Test
////    public void toIMatrix3(){
////
////        Matrix actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).toIMatrix();
////        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName()));
////
////        assertEquals(expected, actual);
////    }
////
////
////    @Test
////    public void toIMatrix4(){
////
////        Matrix actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).toIMatrix();
////        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName()));
////
////        assertEquals(expected, actual);
////    }
////
////
////    @Test
////    public void toIMatrix5(){
////
////        Matrix actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).toIMatrix();
////        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName()));
////
////        assertEquals(expected, actual);
////    }
////
////
////    @Test
////    public void getRank1(){
////
////        int actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getRank().getNumerator();
////        int expected = 2;
////
////        assertEquals(expected, actual);
////    }
////
////
////    @Test
////    public void getRank2(){
////
////        int actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getRank().getNumerator();
////        int expected = 4;
////
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getRank3(){
////
////        int actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getRank().getNumerator();
////        int expected = 3;
////
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getRank4(){
////
////        int actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getRank().getNumerator();
////        int expected = 3;
////
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getRank5(){
////
////        int actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getRank().getNumerator();
////        int expected = 3;
////
////        assertEquals(expected, actual);
////    }
////
////
////    @Test
////    public void getRank6(){
////
////        int actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getRank().getNumerator();
////        int expected = 2;
////
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getRank7(){
////
////        int actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getRank().getNumerator();
////        int expected = 3;
////
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getDeterminantByTriangleMatrix1(){
////
////        Rational actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getDeterminantByTriangleMatrix();
////        Rational expected = new Rational(0);
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getDeterminantByTriangleMatrix2(){
////
////        Rational actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getDeterminantByTriangleMatrix();
////        Rational expected = new Rational(-1034);
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getDeterminantByTriangleMatrix3(){
////
////        Rational actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getDeterminantByTriangleMatrix();
////        Rational expected = new Rational(-42);
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getDeterminantByTriangleMatrix4(){
////
////        Rational actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getDeterminantByTriangleMatrix();
////        Rational expected = new Rational(24);
////        assertEquals(expected, actual);
////    }
////
////
////    @Test
////    public void getDeterminantByTriangleMatrix5(){
////        Matrix matrix = GsonParser.matrixFromJson(getJsonStringIn(getMethodName()));
////        assertThrows(IllegalArgumentException.class, ()-> matrix.getDeterminantByTriangleMatrix());
////    }
////
////    @Test
////    public void getDeterminantByTriangleMatrix6(){
////        Rational actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getDeterminantByTriangleMatrix();
////        Rational expected = new Rational(0);
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getSolutionOfLES1(){
////
////        String actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getSolutionOfLES().toString();
////        String expected = "Independent variables:\n" +
////                "X1 = (-3/2)\n" +
////                "X2 = (1/1)\n" +
////                "X3 = (1/2)\n" +
////                "\n" +
////                "Dependent variables:\n";
////
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getSolutionOfLES2(){
////
////        String actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName()))
////                                        .getSolutionOfLES()
////                                        .toString();
////        String exprected = "Independent variables:\n" +
////                "X1 = (9/4) + (-2/1)*X2 + (-5/4)*X5\n" +
////                "X3 = (3/4) + (-3/4)*X5\n" +
////                "X4 = (-1/2) + (-1/2)*X5\n" +
////                "\n" +
////                "Dependent variables:\n" +
////                "X2 = a1\n" +
////                "X5 = a2\n";
////        assertEquals(exprected, actual);
////
////    }
////
////
////
////    @Test
////    public void getTransposeMatrix1(){
////        Matrix actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getTransposeMatrix();
////        Matrix expected = GsonParser.matrixFromJson(getJsonStringOut(getMethodName()));
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getDecompositionOfVectorsByLinearIndependent1(){
////        String actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getDecompositionOfVectorsByLinearIndependent();
////        String expected = "Basis:\n" +
////                "vector2\n" +
////                "vector4\n" +
////                "\n" +
////                "Decomposition by basis:\n" +
////                "vector1 = (0/1)*vector2 + (0/1)*vector4\n" +
////                "vector2 = (1/1)*vector2 + (0/1)*vector4\n" +
////                "vector3 = (-1/1)*vector2 + (0/1)*vector4\n" +
////                "vector4 = (0/1)*vector2 + (1/1)*vector4\n" +
////                "vector5 = (0/1)*vector2 + (2/1)*vector4\n";
////        assertEquals(expected, actual);
////    }
////
////    @Test
////    public void getDecompositionOfVectorsByLinearIndependent2(){
////        String actual = GsonParser.matrixFromJson(getJsonStringIn(getMethodName())).getDecompositionOfVectorsByLinearIndependent();
////        String expected = "Basis:\n" +
////                "vector1\n" +
////                "vector2\n" +
////                "vector4\n" +
////                "\n" +
////                "Decomposition by basis:\n" +
////                "vector1 = (1/1)*vector1 + (0/1)*vector2 + (0/1)*vector4\n" +
////                "vector2 = (0/1)*vector1 + (1/1)*vector2 + (0/1)*vector4\n" +
////                "vector3 = (0/1)*vector1 + (5645465/1)*vector2 + (0/1)*vector4\n" +
////                "vector4 = (0/1)*vector1 + (0/1)*vector2 + (1/1)*vector4\n" +
////                "vector5 = (-10/1)*vector1 + (7/1)*vector2 + (6/1)*vector4\n";
////        assertEquals(expected, actual);
////    }


}