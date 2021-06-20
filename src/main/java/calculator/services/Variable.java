package calculator.services;



import calculator.Token;


public class Variable {

    public String name;
    public calculator.Token value;

    public Variable(String name, Token value){
        this.name = name;
        this.value = value;
    }

    public Variable(){ }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Token getValue() {
        return value;
    }

    public void setValue(Token value) {
        this.value = value;
    }
}
