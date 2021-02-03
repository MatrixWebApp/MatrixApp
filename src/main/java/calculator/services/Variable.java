package calculator.services;



import calculator.Token;


public class Variable {

    public String name;
    public Token.Type type;
    public calculator.Token value;

    public Variable(String name, Token.Type type, Token value){
        this.name = name;
        this.type = type;
        this.value = value;
    }
}
