package calculator;



import calculator.view.Variable;
import rational.Rational;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator extends Operators {

    public ArrayList<Variable> variables;
    public String status = "OK";

    public Calculator(ArrayList<Variable> variables){
        this.variables = variables;
    }


    public Token evaluate(String expression) throws EmptyStackException, NumberFormatException, NullPointerException {

        try {

            Stack<String> stack = new Stack<>();
            ArrayList<String> list = new ArrayList<>();
            ArrayList<String> expressionTokens = parse(expression);

            try {
                for (String token : expressionTokens) {

                    if (token.compareTo(")") == 0) {    // token - открывающая скобка
                        // выпихиваем до открывающей скобки
                        while (stack.peek().compareTo("(") != 0) {
                            list.add(stack.pop());
                        }
                        stack.pop();
                    } else if (operators.containsKey(token)) {       // token - оператор
                        // выпихиваем из стека элементы по рангу меньшиие чем наш token
                        while ((!stack.isEmpty()) && (stack.peek().compareTo("(") != 0) &&
                                (getRank(stack.peek()) >= getRank(token))) {
                            list.add(stack.pop());
                        }
                        stack.add(token);
                    } else {                                 // token - число
                        list.add(token);
                    }
                }
            }
             catch (EmptyStackException exc) {
                throw new Exception("wrong input");
            }

            // выпихиваем оставшуююся часть из stack в list
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }

            // отправляем результат
            return evaluatePolishNotation(list);

        }
        catch(Exception exc){
            status = exc.getMessage();
            return new Token();
        }
    }
    // парсинг выражения на лексемы
    private ArrayList<String> parse(String expression) throws EmptyStackException, NumberFormatException, NullPointerException {
        ArrayList<String> arrayList = new ArrayList<>();
        while ("".compareTo(expression) != 0) {
            String token = getToken(expression);
            expression = expression.substring(token.length());  // меняем expression
            if (token.compareTo("-") == 0) {                     // если унарный минус
                boolean isUnary = false;
                if (arrayList.size() == 0) {
                    isUnary = true;
                } else if (arrayList.get(arrayList.size() - 1).compareTo("(") == 0) {
                    isUnary = true;
                }
                if (isUnary) {
                    token = getToken(expression);
                    expression = expression.substring(token.length());  // меняем expression
                    arrayList.add("(");
                    arrayList.add("0");
                    arrayList.add("-");
                    arrayList.add(token);
                    arrayList.add(")");
                } else {
                    arrayList.add(token);
                }
            } else {
                arrayList.add(token);
            }
        }
        return arrayList;

    }

    private String getToken(String expression) throws EmptyStackException, NumberFormatException, NullPointerException {
        // ищем оператор в начале выражения
        for (String operator : operators.keySet()) {
            if (expression.startsWith(operator)) {
                return operator;
            }
        }


        for (Variable variable: variables){
            if (expression.startsWith(variable.name)) {
                return variable.name;
            }
        }

        // ищем число
        int startIndex = 0;
        for (startIndex = 0; startIndex < expression.length(); startIndex++) {
            if ((expression.charAt(startIndex) < '0') || (expression.charAt(startIndex) > '9')) {
                break;
            }
        }
        if (startIndex == 0) throw new NullPointerException("wrong input");
        return expression.substring(0, startIndex);

    }

    private static String getStackInfo(Stack<String> stack) {
        ArrayList<String> buffer = new ArrayList<>();
        String result = "";
        while (stack.empty()) {
            buffer.add(stack.pop());
        }
        for (int i = 0; i < buffer.size(); i++) {
            result += buffer.get(buffer.size() - i - 1) + " | ";
            stack.push(buffer.get(buffer.size() - i - 1));
        }
        return result;
    }

    private static String getListInfo(ArrayList<String> list) {
        String result = "";
        for (String str : list) {
            result += str + " | ";
        }
        return result;
    }

    private Token evaluatePolishNotation(ArrayList<String> list) throws EmptyStackException, NumberFormatException, NullPointerException {
        Stack<Token> stack = new Stack<>();
        for (String token : list) {
            if (isOperator(token)) {            // если token - оператор
                if (isBinaryOperator(token)) {
                    try {
                        stack.add(calculate(stack.pop(), stack.pop(), token)); // считаем
                    }
                    catch (EmptyStackException exc){
                        throw new IllegalArgumentException("wrong input");
                    }
                } else {
                    stack.add(calculate(stack.pop(), token)); // token - функция
                }
            } else if (isNumber(token)) {    // если token - число
                stack.add(new Rational(toInt(token)));
            } else if (containsKey(token)){
                if (getToken(token) == null) throw new NullPointerException("unknown variable in input");
                stack.add(get(token));
            } else{
                throw new IllegalArgumentException("wrong expression");
            }
        }
        return stack.peek();
    }

    private static int toInt(String number){
        return number.charAt(0) == '-' ? -Integer.valueOf(number.substring(1)) :
                Integer.valueOf(number);
    }

    private static boolean isNumber(String object){
        try {
            Integer.valueOf(object);
            return true;
        } catch(NumberFormatException ex){
            return false;
        }
    }

    private  boolean containsKey(String key){
        for (Variable variable: variables){
            if (variable.name.compareTo(key) == 0){
                return true;
            }
        }
        return false;
    }

    private Token get(String key){
        for (Variable variable: variables){
            if (variable.name.compareTo(key) == 0){
                return variable.value;
            }
        }
        return null;
    }
}
