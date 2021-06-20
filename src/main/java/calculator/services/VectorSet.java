package calculator.services;


import matrix.Matrix;
import fraction.Fraction;

import java.util.ArrayList;
import java.util.TreeMap;

public class VectorSet {
    public TreeMap<String, ArrayList<Fraction>> vectors = new TreeMap<>();


    // basis - имена базисных векторов
    public String getDecompositionByBasis(ArrayList<String> basisVectorNames){
        StringBuilder result = new StringBuilder();
        ArrayList<ArrayList<Fraction>> mainMatrix = new ArrayList<>();
        for (String name: basisVectorNames){
            mainMatrix.add(vectors.get(name));
        }
        // транспонированный vector set
        mainMatrix = getTransposeList(mainMatrix);

        result.append("Basis:"+"\n");
        for (String basis: basisVectorNames) result.append(basis+"\n");
        result.append("\nDecomposition by basis:\n");

        for (String name: vectors.keySet()){
            result.append(name + " = ");

            ArrayList<ArrayList<Fraction>> augmentedMatrix = new ArrayList<>();
            augmentedMatrix.add(vectors.get(name));
            augmentedMatrix = getTransposeList(augmentedMatrix);

            // создаём матрицу из столбцов-векторов. {v1,v2,v3...} | {v}, где v - раскладываемый вектор
            Matrix matrix = new Matrix(mainMatrix, augmentedMatrix);
            matrix.setColumnNames(basisVectorNames);

            Solution solution = matrix.getSolutionOfLES();
            for (String vector: solution.independentVar.keySet()){
                result.append(solution.independentVar.get(vector).get(0).value+"*"+vector + " + ");
            }
            result.delete(result.length()-3,result.length());
            result.append("\n");
        }
        return result.toString();
    }

    public void addVector(String name, ArrayList<Fraction> value){
        vectors.put(name, value);
    }

    public String toString(){
        StringBuilder result = new StringBuilder("Independent vectors:" + "\n");
        for (String key: vectors.keySet()){
            result.append(key + " = ");
            for (int i = 0; i < vectors.get(key).size(); i++){
//                result.append((vectors.get(key).get(i).getNumerator() == 0) ?
//                                        "":vectors.get(key).get(i) + ("*"+names.get(i)+ " + "));
            }
            result.delete(result.length()-3, result.length());
            result.append("\n");
        }
        return result.toString();
    }

    public ArrayList<ArrayList<Fraction>> getTransposeList(ArrayList<ArrayList<Fraction>> list){
        return (new Matrix(list).getTransposeMatrix().main);
    }
}
