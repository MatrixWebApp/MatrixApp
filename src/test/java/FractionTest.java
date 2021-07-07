
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fraction.Fraction;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FractionTest {

    private Random random;

    @BeforeEach
    public void setup(){
        random = new Random();
    }

    @Test
    public void isEqual() {
        Fraction actual = new Fraction(30,5);
        Fraction expected = new Fraction(6,1);
        assertEquals(actual, expected);
    }

    @Test
    public void lсm() {
        int actual = Fraction.lcm(5,7);
        int expected = 35;
        assertEquals(actual, expected);
    }

    @Test
    public void lсm1() {
        int actual = Fraction.lcm(1,5);
        int expected = 5;
        assertEquals(actual, expected);
    }

    @Test
    public void lсm2() {
        int actual = Fraction.lcm(6,7);
        int expected = 42;
        assertEquals(actual, expected);
    }

    @Test
    public void add() {
        Fraction actual = new Fraction(3,6).add(new Fraction(4,7));
        Fraction expected = new Fraction(15,14);
        assertEquals(actual, expected);
    }

    @Test
    public void add1() {
        Fraction actual = new Fraction(3,6).add(new Fraction(-3,7));
        Fraction expected = new Fraction(3,42);
        assertEquals(actual, expected);
    }

    @Test
    public void add2() {
        Fraction actual = new Fraction(4).add(new Fraction(-0));
        Fraction expected = new Fraction(4);
        assertEquals(actual, expected);
    }


    @Test
    public void sub() {
        Fraction actual = new Fraction(3,8).sub(new Fraction(9,24));
        Fraction expected = new Fraction(0,1);
        assertEquals(actual, expected);
    }


    @Test
    public void sub1() {
        Fraction actual = new Fraction(-8,9).sub(new Fraction(-3,7));
        Fraction expected = new Fraction(-29,63);
        assertEquals(actual, expected);
    }

    @Test
    public void sub2() {
        Fraction actual = new Fraction().sub(new Fraction(4,7));
        Fraction expected = new Fraction(-4,7);
        assertEquals(actual, expected);
    }

    @Test
    public void sub3() {
        Fraction actual = new Fraction().sub(new Fraction());
        Fraction expected = new Fraction();
        assertEquals(actual, expected);
    }

    @Test
    public void div(){
        Fraction actual = new Fraction().div(new Fraction(1));
        Fraction expected = new Fraction();
        assertEquals(actual, expected);
    }
    @Test
    public void div1(){
        Fraction actual = new Fraction(-14,14).div(new Fraction(-14,14));
        Fraction expected = new Fraction(1);
        assertEquals(actual, expected);
    }

    @Test
    public void div2() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () ->
                      {new Fraction(0).div(new Fraction(0));});
    }

    @Test void div3(){

        Fraction fraction1 = new Fraction(random.nextInt(1000)+1, random.nextInt(1000)+1);
        Fraction fraction2 = new Fraction(random.nextInt(1000)+1, random.nextInt(1000)+1);

        Fraction actual = fraction1.mlp(fraction2);
        Fraction expected = fraction1.div(new Fraction(1).div(fraction2));
        assertEquals(expected,actual);
    }




}