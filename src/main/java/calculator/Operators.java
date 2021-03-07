package calculator;

import java.util.HashMap;

public class Operators {

    static protected HashMap<String, Integer> operators;

    static {
        operators = new HashMap<>();
        operators.put("-", 1);
        operators.put("+", 1);
        operators.put("*", 2);
        operators.put("/", 2);
        operators.put("^", 3);
        operators.put("inverse",3);
        operators.put("trans",3);
        operators.put("det",3);
        operators.put("triangle",3);
        operators.put("rank",3);
        operators.put("(", 4);
        operators.put(")", 4);

    }

    static protected int getRank(String operator){
        return operators.get(operator);
    }

    static protected boolean isOperator(String operator){
        return operators.containsKey(operator);
    }

    static protected Token calculate(Token rightO, Token leftO, String operator){
        Token result;

        switch(operator){
            case "+":
                result = leftO.add(rightO);
                break;
            case "-":
                result = leftO.sub(rightO);
                break;
            case "*":
                result = leftO.mlp(rightO);
                break;
            case "^":
                result = leftO.pow(rightO);
                break;
            case "/":
                result = leftO.div(rightO);
                break;
            default:
                throw new IllegalArgumentException("Didn't define operator" + "(" + operator + ") " +
                        "for the arguments in calculate\n");
        }
        assert result != null;
        return result;
    }

    static protected Token calculate(Token operand, String function){
        Token result;
        switch(function){
            case "inverse": {
                result = operand.getInverseMatrix();
                break;
            }
            case "det":{
                result = operand.getDeterminant();
                break;
            }
            case "trans":{
                result = operand.getTransposeMatrix();
                break;
            }
            case "triangle":{
                result = operand.getTriangleMatrix();
                break;
            }
            case "rank":{
                result = operand.getRankOfMatrix();
                break;
            }
            default:
                throw new IllegalArgumentException("wrong input");

        }
        return result;
    }

    static protected boolean isBinaryOperator(String operator){
        if (("+".compareTo(operator) == 0) || ("*".compareTo(operator) == 0) ||
            ("-".compareTo(operator) == 0) || ("/".compareTo(operator) == 0) ||
            ("^".compareTo(operator) == 0)){
            return true;
        }
        return false;
    }








}
