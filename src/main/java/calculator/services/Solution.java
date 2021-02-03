package calculator.services;


import rational.Rational;

import java.util.List;
import java.util.TreeMap;

public class Solution {
    public TreeMap<String, List<RationalVar>> independentVar = new TreeMap<>();
    public TreeMap<String, List<RationalVar>> dependentVar = new TreeMap<>();
    public boolean isExist;

    public Solution(boolean isExist){
        this.isExist = isExist;
    }




    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Independent variables:" + "\n");
        for (String var: independentVar.keySet()) {
            result.append(var + " = ");
            for (int i = 0; i < independentVar.get(var).size(); i++) {
                RationalVar rationalVar = independentVar.get(var).get(i);
                if (!new Rational(0).equals(rationalVar.value)) {
                    result.append(rationalVar.value+
                            (rationalVar.name.compareTo("") == 0 ? "" : "*" + rationalVar.name) +
                            " + ");
                }
            }
            result.delete(result.length()-3, result.length());
            result.append("\n");
        }
        result.append("\n");
        result.append("Dependent variables:" + "\n");
        int i = 0;
        for (String var: dependentVar.keySet()) {
            i++;
            result.append(var + " = " + "a"+i + "\n");
        }

        return isExist ? result.toString(): "System of Linear Equation has not solution";
    }

}

