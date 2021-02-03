package calculator.services;

import java.io.BufferedReader;
import java.io.IOException;

public class Stringer {
    static public String toString(BufferedReader reader) throws IOException {
        StringBuilder result = new StringBuilder();
        reader.lines().forEach((String a)  -> result.append(a));
        return String.valueOf(result);
    }
}
