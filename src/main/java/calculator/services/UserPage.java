package calculator.services;

import java.util.ArrayList;

public class UserPage {
    public int list;
    public String expression;
    public ArrayList variables;

    public UserPage(int list, String expression, ArrayList variables){
        this.list = list;
        this.expression = expression;
        this.variables = variables;
    }
}
