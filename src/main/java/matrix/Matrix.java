package matrix;

import calculator.services.RationalVar;
import calculator.services.Solution;
import calculator.services.VectorSet;
import rational.Rational;

import java.util.*;

public class Matrix implements MatrixOperators {

    public ArrayList<ArrayList<Rational>> mainMatrix = new ArrayList<>();
    private ArrayList<ArrayList<Rational>> augmentedMatrix = new ArrayList<>();

    private ArrayList<String> columnNames = new ArrayList<>();
    private ArrayList<String> rowNames = new ArrayList<>();



    public Matrix(int height, int width) throws IllegalArgumentException{
        if ((height <= 0) || (width <= 0)){
            throw new IllegalArgumentException("invalid size for matrix");
        }
        for (int i = 1; i <= width; i++) {
            columnNames.add("X"+i);
        }
        for (int i = 1; i <= height; i++){
            rowNames.add("vector"+i);
        }

        for (int i = 0; i < height; i++){
            mainMatrix.add(new ArrayList<>());
            for (int j = 0; j < width; j++){
                mainMatrix.get(i).add(new Rational(0));
            }
        }
    }

    public Matrix(int mainHeight, int mainWidth, int augmentedHeight, int augmentedWidth) throws IllegalArgumentException{
        this(mainHeight, mainWidth);

        if (augmentedHeight < 0 || augmentedWidth < 0 || augmentedWidth != 0 && augmentedHeight != mainHeight){
            throw new IllegalArgumentException("invalid size for matrix");
        }


        for (int i = 0; i < augmentedHeight; i++){
            augmentedMatrix.add(new ArrayList<>());
            for (int j = 0; j < augmentedWidth; j++){
                augmentedMatrix.get(i).add(new Rational(0));
            }
        }
    }


    public Matrix(ArrayList<ArrayList<Rational>> mainMatrix){
        this(mainMatrix.size(), mainMatrix.get(0).size());

        for (int i = 0; i < mainMatrix.size(); i++){
            for (int j = 0; j < mainMatrix.get(i).size(); j++){
                this.mainMatrix.get(i).set(j, new Rational(mainMatrix.get(i).get(j)));
            }
        }
    }

    public Matrix(ArrayList<ArrayList<Rational>> mainMatrix, ArrayList<ArrayList<Rational>> augmentedMatrix){
        this(mainMatrix);
        for (int i = 0; i < augmentedMatrix.size(); i++){
            this.augmentedMatrix.add(new ArrayList<>());
            for (int j = 0; j < augmentedMatrix.get(i).size(); j++){
                this.augmentedMatrix.get(i).add(new Rational(augmentedMatrix.get(i).get(j)));
            }
        }
    }

    public Matrix(Matrix matrix){
        this(matrix.mainMatrix, matrix.augmentedMatrix);
        rowNames = new ArrayList<>(matrix.rowNames);
        columnNames = new ArrayList<>(matrix.columnNames);
    }


    public void setColumnNames(ArrayList<String> columnNames) {
        this.columnNames = columnNames;
    }
    public void setRowNames(ArrayList<String> rowNames) {
        this.rowNames = rowNames;
    }

    public int getWidthMainMatrix(){
        return mainMatrix.get(0).size();
    }
    public int getHeightMainMatrix(){
        return mainMatrix.size();
    }
    public int getWidthAugmentedMatrix(){
        return augmentedMatrix.size() == 0 ? 0:augmentedMatrix.get(0).size();
    }
    public int getHeightAugmentedMatrix(){
        return augmentedMatrix.size();
    }


    public void swipeColumn(int j1, int j2){
        if ((j1 < 0) || (j2 < 0) || (j1 >= getWidthMainMatrix()) || (j2 >= getWidthMainMatrix())) {
            throw new IllegalArgumentException("invalid arguments in swipeRow\n");
        }
        for (int i = 0; i < getHeightMainMatrix(); i++){
            Rational.swap(mainMatrix.get(i).get(j1), mainMatrix.get(i).get(j2));
        }

        String tmp = columnNames.get(j1);
        columnNames.set(j1, columnNames.get(j2));
        columnNames.set(j2, tmp);

    }
    public void swipeRow(int i1, int i2) {
        if ((i1 < 0) || (i2 < 0) || (i1 >= getHeightMainMatrix()) || (i2 >= getHeightMainMatrix())) {
            throw new IllegalArgumentException("invalid arguments in swipeRow\n");
        }
        for (int j = 0; j < getWidthMainMatrix(); j++) {
            Rational.swap(mainMatrix.get(i1).get(j), mainMatrix.get(i2).get(j));
        }
        for (int j = 0; j < getWidthAugmentedMatrix(); j++) {
            Rational.swap(augmentedMatrix.get(i1).get(j), augmentedMatrix.get(i2).get(j));
        }
        // обмен строк
        String tmp = rowNames.get(i1);
        rowNames.set(i1, rowNames.get(i2));
        rowNames.set(i2, tmp);
    }


    private int getVerticalCandidate(int startI, int j){
        for (int i = startI; i < getHeightMainMatrix(); i++){
            if (getMainMatrixElement(i,j).isNotEqual(new Rational(0))){
                return i;
            }
        }
        return -1;
    }
    private int getHorizonCandidate(int i, int startJ){
        for (int j = startJ; j < getWidthMainMatrix(); j++){
            if (getMainMatrixElement(i,j).isNotEqual(new Rational(0,1))){
                return j;
            }
        }
        return -1;
    }

    public void addRow(int target, int source, Rational coef){
        for (int j = 0; j < getWidthMainMatrix(); j++){
            mainMatrix.get(target).set(j, mainMatrix.get(target).get(j).add(mainMatrix.get(source).get(j).mlp(coef)));
        }
        for (int j = 0; j < getWidthAugmentedMatrix(); j++){
            augmentedMatrix.get(target).set(j, augmentedMatrix.get(target).get(j).add(augmentedMatrix.get(source).get(j).mlp(coef)));
        }
    }
    public void mlpRow(int target, Rational rightOperand){

        for (int j = 0; j < getWidthMainMatrix(); j++){
            mainMatrix.get(target).set(j, mainMatrix.get(target).get(j).mlp(rightOperand));
        }
        for (int j = 0; j < getWidthAugmentedMatrix(); j++){
            augmentedMatrix.get(target).set(j, augmentedMatrix.get(target).get(j).mlp(rightOperand));
        }

    }


    public Rational getDeterminantByTriangleMatrix() throws IllegalArgumentException{
        // one of the determinant property:
        // Interchanging any pair of columns or rows of a matrix multiplies its determinant by −1
        int countOfInterchanging = 0;

        if (getHeightMainMatrix() != getWidthMainMatrix()) {
            throw new IllegalArgumentException("invalid matrix in getTriangleMatrix");
        }
        Matrix matrix = new Matrix(this);

        int i = 0;
        boolean isNullMatrix = false;

        while ((!isNullMatrix) && (i < Math.min(matrix.getHeightMainMatrix(), matrix.getWidthMainMatrix()))) {
            if (matrix.getHorizonCandidate(i, i) != -1) {
                //не нули по горизонтали
                int candidate =  matrix.getHorizonCandidate(i, i);
                if (candidate != i) {
                    matrix.swipeColumn(i, matrix.getHorizonCandidate(i, i));
                    countOfInterchanging++;
                }
            } else {
                if (matrix.getVerticalCandidate(i, i) != -1) {
                    //нули по горизонтали, но не нули по вертикали
                    int candidate = matrix.getVerticalCandidate(i, i);
                    if (i != candidate) {
                        matrix.swipeRow(i, candidate);
                        countOfInterchanging++;
                    }

                } else {
                    // крест смерти
                    // предположим, что матрица нулевая
                    isNullMatrix = true;
                    for (int i1 = i + 1; isNullMatrix && (i1 < matrix.getHeightMainMatrix()); i1++) {
                        if (matrix.getHorizonCandidate(i1, i + 1) != -1) {
                            matrix.swipeRow(i, i1);
                            countOfInterchanging++;

                            int candidate = matrix.getHorizonCandidate(i, i + 1);
                            if (candidate != i) {
                                matrix.swipeColumn(i, candidate);
                                countOfInterchanging++;
                            }
                            isNullMatrix = false;
                        }
                    }
                }
            }

            if (!isNullMatrix) {
                // делаем нули внизу
                for (int candidateRow = i + 1; candidateRow < matrix.getHeightMainMatrix(); candidateRow++) {
                    matrix.addRow(candidateRow, i, new Rational(1).div(matrix.getMainMatrixElement(i,i)).mlp(matrix.getMainMatrixElement(candidateRow, i)).neg());
                }
            }
            // переходим к следующей ступени
            i++;
        }

        Rational determinant = matrix.getDeterminantOfTriangleMatrix();

        if (countOfInterchanging % 2 == 1){
            determinant = determinant.mlp(new Rational(-1));
        }

        return determinant;

    }

    public Matrix getTriangleMatrix() throws IllegalArgumentException{

        if (getHeightMainMatrix() != getWidthMainMatrix()) {
            throw new IllegalArgumentException("invalid matrix in getTriangleMatrix");
        }
        Matrix matrix = new Matrix(this);

        int i = 0;
        boolean isNullMatrix = false;

        while ((!isNullMatrix) && (i < Math.min(matrix.getHeightMainMatrix(), matrix.getWidthMainMatrix()))) {
            if (matrix.getHorizonCandidate(i, i) != -1) {
                //не нули по горизонтали
                matrix.swipeColumn(i, matrix.getHorizonCandidate(i, i));
            } else {
                if (matrix.getVerticalCandidate(i, i) != -1) {
                    //нули по горизонтали, но не нули по вертикали
                    matrix.swipeRow(i, matrix.getVerticalCandidate(i, i));
                } else {
                    // крест смерти
                    // предположим, что матрица нулевая
                    isNullMatrix = true;
                    for (int i1 = i + 1; (isNullMatrix) && (i1 < matrix.getHeightMainMatrix()); i1++) {
                        if (matrix.getHorizonCandidate(i1, i + 1) != -1) {
                            matrix.swipeRow(i, i1);
                            matrix.swipeColumn(i, matrix.getHorizonCandidate(i, i + 1));
                            isNullMatrix = false;
                        }
                    }
                }
            }

            if (!isNullMatrix) {
                // делаем нули внизу
                for (int candidateRow = i + 1; candidateRow < matrix.getHeightMainMatrix(); candidateRow++) {
                    matrix.addRow(candidateRow, i, new Rational(1).div(matrix.getMainMatrixElement(i,i)).mlp(matrix.getMainMatrixElement(candidateRow, i)).neg());
                }
            }
            // переходим к следующей ступени
            i++;
        }
        return matrix;
    }

    public Rational getDeterminantOfTriangleMatrix(){
        Rational det = new Rational(1);
        for (int i = 0; i < getHeightMainMatrix(); i++){
            det.setRational(det.mlp(getMainMatrixElement(i,i)));
        }
        return det;
    }

    public Rational getMainMatrixElement(int i, int j){
        if ((i < 0) || (j < 0) || (i >= getHeightMainMatrix()) || (j >= getWidthMainMatrix())) {
            throw new IllegalArgumentException("invalid arguments in getMainMatrixElement\n");
        }
        return new Rational(mainMatrix.get(i).get(j));
    }

    public Rational getAugmentedElement(int i, int j){
        if ((augmentedMatrix.size() == 0) || (i < 0) || (j < 0) || (i >= getHeightAugmentedMatrix()) || (j >= getWidthAugmentedMatrix())){
            throw new IllegalArgumentException("invalid arguments in getMainMatrixElement\n");
        }
        return new Rational(augmentedMatrix.get(i).get(j));
    }

    public ArrayList<Rational> matrixFromJsonRow(int i){
        if ((i < 0) || (i >= getHeightMainMatrix())) {
            throw new IllegalArgumentException("invalid arguments in matrixFromJsonRow\n");
        }
        return new ArrayList<Rational>(mainMatrix.get(i));
    }

    public Solution getSolutionOfLES(){
        // открываем лист переменных

        Matrix matrix = toIMatrix();

        for (int i = 0; i < matrix.getHeightMainMatrix(); i++){
            // решений нет
             if ((!matrix.getAugmentedElement(i,0).equals(new Rational(0))) && (matrix.getHorizonCandidate(i,0) == -1)){
                return new Solution(false);
            }
        }

        // создаём решение, которое существует. Инициализируем его именами колонок
        Solution solution = new Solution(true);

        int rank = this.getRank().getNumerator();
        for (int i = 0; i < rank; i++){

            String variableName = matrix.columnNames.get(i);
            solution.independentVar.put(variableName, new ArrayList<>());
            int horizontalCandidate = matrix.getHorizonCandidate(i, 0);

            solution.independentVar.get(variableName).add(new RationalVar("", matrix.getAugmentedElement(i,0)));
            for (int j = horizontalCandidate+1; j < matrix.getWidthMainMatrix(); j++){
                String name = matrix.columnNames.get(j);
                Rational value = matrix.getMainMatrixElement(i,j);
                solution.independentVar.get(variableName).add(new RationalVar(name,value.neg()));
            }
        }

        for (int i = rank; i < getWidthMainMatrix(); i++){
            String variableName = matrix.columnNames.get(i);
            solution.dependentVar.put(variableName, new ArrayList<>());
        }

        return solution;
    }

    public String getDecompositionOfVectorsByLinearIndependent(){
        Matrix IMatrix = toIMatrix();

        VectorSet vectorSet = new VectorSet();
        for (int i = 0; i < getHeightMainMatrix(); i++){
            vectorSet.addVector(rowNames.get(i), mainMatrix.get(i));
        }

        ArrayList<String> basisNamesList = new ArrayList<>();
        for (int i = 0; i < IMatrix.getRank().getNumerator(); i++) {
            basisNamesList.add(IMatrix.rowNames.get(i));
        }

        return vectorSet.getDecompositionByBasis(basisNamesList);
    }

    public Rational getDeterminantByPermutations(){
        if (getHeightMainMatrix() != getWidthMainMatrix()){
            throw new IllegalArgumentException("invalid matrix in getDeterminantByPermutation\n");
        }
        return null;
    }

    public Rational getRank(){
        Matrix matrix = new Matrix(this);
        int rank = 0;

        matrix = matrix.toIMatrix();

        for (int i = 0; i < matrix.getHeightMainMatrix(); i++){
            if (matrix.getHorizonCandidate(i,0) != -1){
                rank++;
            }
        }
        return new Rational(rank);
    }

    public static int countInversions(ArrayList<Integer> p){
        return 0;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < getHeightMainMatrix(); i++){
            for (int j = 0; j < getWidthMainMatrix(); j++){
                result.append(getMainMatrixElement(i,j) + " ");
            }
            if ((getWidthAugmentedMatrix() != 0)){
                result.append(" | ");
            }
            for (int j = 0; j < getWidthAugmentedMatrix(); j++){
                result.append(getAugmentedElement(i,j) + " ");
            }
            result.append("\n");
        }
        return result.toString();
    }



    @Override
    public Matrix pow(Rational rational) throws IllegalArgumentException{
        if (rational.getNumerator() < 0){
            throw new IllegalArgumentException("negative base in Matrix.pow");
        }
        Matrix result = new Matrix(this);
        for (int i = 1; i < rational.getNumerator(); i++){
            result = result.mlp(this);
        }
        return result;
    }

    @Override
    public Matrix getTransposeMatrix(){
        Matrix matrix = new Matrix(getWidthMainMatrix(), getHeightMainMatrix(), getHeightAugmentedMatrix(), getWidthAugmentedMatrix());
        for (int i = 0; i < getHeightMainMatrix(); i++){
            for (int j = 0; j < getWidthMainMatrix(); j++){
                matrix.mainMatrix.get(j).set(i, new Rational(getMainMatrixElement(i,j)));
            }
        }
        for (int i = 0; i < getHeightAugmentedMatrix(); i++){
            for (int j = 0; j < getWidthAugmentedMatrix(); j++){
                matrix.augmentedMatrix.get(i).set(j, new Rational(getAugmentedElement(i,j)));
            }
        }
        return matrix;
    }

    @Override
    public Matrix getInverseMatrix() throws IllegalArgumentException{
        /// бросить исключение, в случае нуля!!!
        if (getDeterminantByTriangleMatrix().equals(new Rational(0))){
            throw new IllegalArgumentException("zero-matrix in getInverseMatrix");
        }

        Matrix A = new Matrix(getHeightMainMatrix(), getWidthMainMatrix(), getHeightMainMatrix(), getWidthMainMatrix());

        for (int i = 0; i < A.getHeightMainMatrix(); i++){
            A.augmentedMatrix.get(i).set(i, new Rational(1));
            for (int j = 0; j < A.getWidthMainMatrix(); j++){
                A.mainMatrix.get(i).set(j, getMainMatrixElement(i,j));
            }
        }

        for (int j = 0; j < A.getHeightMainMatrix(); j++){
            A.swipeRow(j, A.getVerticalCandidate(j,j));
            for (int i = j+1; i < A.getHeightMainMatrix(); i++){
                A.addRow(i, j, A.getMainMatrixElement(i,j)
                        .div(A.getMainMatrixElement(j,j))
                        .neg());
            }
        }
        for (int j = A.getHeightMainMatrix()-1; j >= 0; j--){
            for (int i = 0; i < j; i++){
                A.addRow(i, j, A.getMainMatrixElement(i,j)
                        .div(A.getMainMatrixElement(j,j))
                        .neg());
            }
        }

        for (int i = 0; i < A.getHeightMainMatrix(); i++){
            A.mlpRow(i, new Rational(1).div(A.getMainMatrixElement(i,i)));
        }


        return new Matrix(A.augmentedMatrix);

    }




    @Override
    public Matrix add(Matrix matrix) throws IllegalArgumentException{
        if ((getHeightMainMatrix() != matrix.getHeightMainMatrix()) || (getWidthMainMatrix() != matrix.getWidthMainMatrix()) ||
            (getHeightAugmentedMatrix() != matrix.getHeightAugmentedMatrix()) || (getWidthAugmentedMatrix() != matrix.getWidthAugmentedMatrix())){
            throw new IllegalArgumentException("invalid size of matrix in add\n");
        }

        Matrix result = new Matrix(this);

        for (int i = 0; i < result.getHeightMainMatrix(); i++){
            for (int j = 0; j < result.getWidthMainMatrix(); j++){
                result.mainMatrix.get(i).set(j, result.getMainMatrixElement(i,j)
                                                .add(matrix.getMainMatrixElement(i,j)));
            }
        }

        for (int i = 0; i < result.getHeightAugmentedMatrix(); i++){
            for (int j = 0; j < result.getWidthAugmentedMatrix(); j++){
                result.augmentedMatrix.get(i).set(j, result.getAugmentedElement(i,j)
                                                     .add(getAugmentedElement(i,j)));
            }
        }

        return result;

    }

    @Override
    public Matrix add(Rational rational) {
        Matrix matrix = new Matrix(this);
        for (int i = 0; i < matrix.getHeightMainMatrix(); i++){
            for (int j = 0; j < matrix.getWidthMainMatrix(); j++){
                matrix.mainMatrix.get(i).set(j, getMainMatrixElement(i,j).add(rational));
            }
        }
        for (int i = 0; i < matrix.getHeightAugmentedMatrix(); i++){
            for (int j = 0; j < matrix.getWidthAugmentedMatrix(); j++){
                matrix.augmentedMatrix.get(i).set(j, getAugmentedElement(i,j).add(rational));
            }
        }
        return matrix;
    }

    @Override
    public Matrix neg(){
        return mlp(new Rational(-1));
    }

    @Override
    public Matrix sub(Rational rational) {
        return this.add(rational.neg());
    }

    @Override
    public Matrix mlp(Rational rational) {
        Matrix matrix = new Matrix(this);
        for (int i = 0; i < matrix.getHeightMainMatrix(); i++){
            for (int j = 0; j < matrix.getWidthMainMatrix(); j++){
                matrix.mainMatrix.get(i).set(j, getMainMatrixElement(i,j).mlp(rational));
            }
        }
        for (int i = 0; i < matrix.getHeightAugmentedMatrix(); i++){
            for (int j = 0; j < matrix.getWidthAugmentedMatrix(); j++){
                matrix.augmentedMatrix.get(i).set(j, getAugmentedElement(i,j).mlp(rational));
            }
        }
        return matrix;
    }

    @Override
    public Matrix div(Rational rational) {
        Matrix matrix = new Matrix(this);
        for (int i = 0; i < matrix.getHeightMainMatrix(); i++){
            for (int j = 0; j < matrix.getWidthMainMatrix(); j++){
                matrix.mainMatrix.get(i).set(j, getMainMatrixElement(i,j).div(rational));
            }
        }
        for (int i = 0; i < matrix.getHeightAugmentedMatrix(); i++){
            for (int j = 0; j < matrix.getWidthAugmentedMatrix(); j++){
                matrix.augmentedMatrix.get(i).set(j, getAugmentedElement(i,j).div(rational));
            }
        }
        return matrix;
    }

    @Override
    public Matrix sub(Matrix matrix) {
        if ((getHeightMainMatrix() != matrix.getHeightMainMatrix()) || (getWidthMainMatrix() != matrix.getWidthMainMatrix()) ||
                (getHeightAugmentedMatrix() != matrix.getHeightAugmentedMatrix()) || (getWidthAugmentedMatrix() != matrix.getWidthAugmentedMatrix())){
            throw new IllegalArgumentException("invalid size of matrix in sub\n");
        }

        Matrix result = new Matrix(this);

        for (int i = 0; i < result.getHeightMainMatrix(); i++){
            for (int j = 0; j < result.getWidthMainMatrix(); j++){
                result.mainMatrix.get(i).set(j, result.getMainMatrixElement(i,j)
                        .sub(matrix.getMainMatrixElement(i,j)));
            }
        }

        for (int i = 0; i < result.getHeightAugmentedMatrix(); i++){
            for (int j = 0; j < result.getWidthAugmentedMatrix(); j++){
                result.augmentedMatrix.get(i).set(j, result.getAugmentedElement(i,j)
                        .sub(getAugmentedElement(i,j)));
            }
        }

        return result;

    }






    @Override
    public Matrix mlp(Matrix matrix) throws IllegalArgumentException{
        if (getWidthMainMatrix() != matrix.getHeightMainMatrix()){
            throw new IllegalArgumentException("invalid size of matrix in mlp\n");
        }
        Matrix result = new Matrix(getHeightMainMatrix(), matrix.getWidthMainMatrix());

        for (int i = 0; i < result.getHeightMainMatrix(); i++){
            for (int j = 0; j < result.getWidthMainMatrix(); j++){
                for (int s = 0; s < getHeightMainMatrix(); s++){
                    result.mainMatrix.get(i).set(j, result.getMainMatrixElement(i,j)
                                                    .add(getMainMatrixElement(i,s).mlp(matrix.getMainMatrixElement(s,j))));
                }
            }

        }
        return result;
    }

    @Override
    public Matrix div(Matrix matrix) throws IllegalArgumentException{

        if (getWidthMainMatrix() != matrix.getInverseMatrix().getHeightMainMatrix()){
            throw new IllegalArgumentException("Invalid size of matrix in add\n");
        }
        return this.mlp(matrix.getInverseMatrix());
    }

    @Override
    public Matrix toIMatrix() {

        Matrix matrix = new Matrix(this);

        int i = 0;
        boolean isNullMatrix = false;

        while ((!isNullMatrix) && (i < Math.min(matrix.getHeightMainMatrix(), matrix.getWidthMainMatrix()))) {
            if (matrix.getHorizonCandidate(i, i) != -1) {
                //не нули по горизонтали
                matrix.swipeColumn(i, matrix.getHorizonCandidate(i, i));
            } else {
                if (matrix.getVerticalCandidate(i, i) != -1) {
                    //нули по горизонтали, но не нули по вертикали
                    matrix.swipeRow(i, matrix.getVerticalCandidate(i, i));
                } else {
                    // крест смерти
                    // предположим, что матрица нулевая
                    isNullMatrix = true;
                    for (int i1 = i + 1; (isNullMatrix) && (i1 < matrix.getHeightMainMatrix()); i1++) {
                        if (matrix.getHorizonCandidate(i1, i + 1) != -1) {
                            matrix.swipeRow(i, i1);
                            matrix.swipeColumn(i, matrix.getHorizonCandidate(i, i + 1));
                            isNullMatrix = false;
                        }
                    }
                }
            }


            if (!isNullMatrix) {
                // делаем единицу
                matrix.mlpRow(i, new Rational(1).div(matrix.getMainMatrixElement(i, i)));
                // делаем нули внизу
                for (int candidateRow = i + 1; candidateRow < matrix.getHeightMainMatrix(); candidateRow++) {
                    matrix.addRow(candidateRow, i, matrix.getMainMatrixElement(candidateRow, i).neg());
                }
                // делаем нули наверху
                for (int candidateRow = 0; candidateRow < i; candidateRow++) {
                    matrix.addRow(candidateRow, i, matrix.getMainMatrixElement(candidateRow, i).neg());
                }
            }
            // переходим к следующей ступени
            i++;
        }
        return matrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;


        if ((this.getHeightMainMatrix() != matrix.getHeightMainMatrix()) ||
            (this.getWidthMainMatrix() != matrix.getWidthMainMatrix()) ||
            (this.getWidthAugmentedMatrix() != matrix.getWidthAugmentedMatrix()) ||
            (this.getHeightAugmentedMatrix() != matrix.getHeightAugmentedMatrix())){
            return false;
        }

        for (int i = 0; i < this.getHeightMainMatrix(); i++){
            for (int j = 0; j < this.getWidthMainMatrix(); j++){
                if (!this.getMainMatrixElement(i,j).equals(matrix.getMainMatrixElement(i, j))){
                    return false;
                }
            }
        }

        for (int i = 0; i < this.getHeightAugmentedMatrix(); i++){
            for (int j = 0; j < this.getWidthAugmentedMatrix(); j++){
                if (!this.getAugmentedElement(i,j).equals(matrix.getAugmentedElement(i, j))){
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainMatrix, augmentedMatrix);
    }

}
