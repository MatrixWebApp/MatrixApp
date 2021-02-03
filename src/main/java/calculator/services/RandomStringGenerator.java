package calculator.services;

import java.util.Random;

// генерирует рандомную строку
public class RandomStringGenerator{

    static int size = 30;

    static public String getRandomString(){
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++){
            str.append((char) (97 + random.nextInt(122-97)));
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(getRandomString());
    }

}
