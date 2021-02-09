
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rational.Rational;

import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RationalTest {

    private Random random;

    @BeforeEach
    public void setup(){
        // ./src/main/resources/matrixTest
        random = new Random();
    }

    @Test
    public void isEqual() {
        Rational actual = new Rational(30,5);
        Rational expected = new Rational(6,1);
        assertEquals(actual, expected);
    }

    @Test
    public void lсm() {
        int actual = Rational.lсm(5,7);
        int expected = 35;
        assertEquals(actual, expected);
    }

    @Test
    public void lсm1() {
        int actual = Rational.lсm(1,5);
        int expected = 5;
        assertEquals(actual, expected);
    }

    @Test
    public void lсm2() {
        int actual = Rational.lсm(6,7);
        int expected = 42;
        assertEquals(actual, expected);
    }

    @Test
    public void add() {
        Rational actual = new Rational(3,6).add(new Rational(4,7));
        Rational expected = new Rational(15,14);
        assertEquals(actual, expected);
    }

    @Test
    public void add1() {
        Rational actual = new Rational(3,6).add(new Rational(-3,7));
        Rational expected = new Rational(3,42);
        assertEquals(actual, expected);
    }

    @Test
    public void add2() {
        Rational actual = new Rational(4).add(new Rational(-0));
        Rational expected = new Rational(4);
        assertEquals(actual, expected);
    }


    @Test
    public void sub() {
        Rational actual = new Rational(3,8).sub(new Rational(9,24));
        Rational expected = new Rational(0,1);
        assertEquals(actual, expected);
    }


    @Test
    public void sub1() {
        Rational actual = new Rational(-8,9).sub(new Rational(-3,7));
        Rational expected = new Rational(-29,63);
        assertEquals(actual, expected);
    }

    @Test
    public void sub2() {
        Rational actual = new Rational().sub(new Rational(4,7));
        Rational expected = new Rational(-4,7);
        assertEquals(actual, expected);
    }

    @Test
    public void sub3() {
        Rational actual = new Rational().sub(new Rational());
        Rational expected = new Rational();
        assertEquals(actual, expected);
    }

    @Test
    public void div(){
        Rational actual = new Rational().div(new Rational(1));
        Rational expected = new Rational();
        assertEquals(actual, expected);
    }
    @Test
    public void div1(){
        Rational actual = new Rational(-14,14).div(new Rational(-14,14));
        Rational expected = new Rational(1);
        assertEquals(actual, expected);
    }

    @Test
    public void div2() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () ->
                      {new Rational(0).div(new Rational(0));});
    }

    @Test void div3(){

        Rational rational1 = new Rational(random.nextInt(1000)+1, random.nextInt(1000)+1);
        Rational rational2 = new Rational(random.nextInt(1000)+1, random.nextInt(1000)+1);

        Rational actual = rational1.mlp(rational2);
        Rational expected = rational1.div(new Rational(1).div(rational2));
        assertEquals(expected,actual);
    }




}