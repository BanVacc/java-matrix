package back;

import java.util.InputMismatchException;
import java.util.StringJoiner;


public class Solver {

    /**
     * Класс Solution представляет решение, содержащее текст и матрицу.
     */
    public class Solution {
        String text;
        Matrix matrix;

        /**
         * Возвращает матрицу, содержащуюся в решении.
         *
         * @return матрица в решении
         */
        public Matrix getMatrix() {
            return matrix;
        }

        /**
         * Возвращает текст, содержащийся в решении.
         *
         * @return текст в решении
         */
        public String getText() {
            return text;
        }
    }

    /**
     * Вычисляет скалярное произведение двух векторов размером 1xN.
     *
     * @param matrixA первый вектор размером 1xN
     * @param matrixB второй вектор размером 1xN
     * @return бъект Solution, содержащий текстовое описание решения и матрицу размером 1x1,
     * которая представляет скалярное произведение векторов
     * @throws InputMismatchException если матрицы содержат больше одной строки или размеры матриц не совпадают
     */
    public Solution calculateDotProduct(Matrix matrixA, Matrix matrixB) throws InputMismatchException {

        if (matrixA.getRowCount() > 1 || matrixB.getRowCount() > 1) {
            throw new InputMismatchException("Матрицы должны содержать одну строку");
        }

        if (matrixA.getColumnCount() != matrixB.getColumnCount()) {
            throw new InputMismatchException("Размеры матриц не совпадают");
        }

        double dotProduct = 0;
        StringJoiner joiner = new StringJoiner(" + ");

        for (int col = 0; col < matrixA.getColumnCount(); col++) {
            double a = matrixA.getElementAt(0, col);
            double b = matrixB.getElementAt(0, col);

            joiner.add(String.format("(%f * %f)", a, b));
            dotProduct += a * b;
        }

        Solution solution = new Solution();
        solution.text = joiner.toString() + String.format(" = %f", dotProduct);
        solution.matrix = new Matrix(1, 1);
        solution.matrix.setElementAt(0, 0, dotProduct);

        return solution;
    }

    /**
     * Вычисляет скалярное произведение двух векторов
     *
     * @param matrixA первый вектор размером 1x3
     * @param matrixB второй вектор размером 1x3
     * @return бъект Solution, содержащий текстовое описание решения и матрицу размером 1x3,
     * которая представляет вектроное произведение векторов
     * @throws InputMismatchException если размеры матрицы отличны от 1x3
     */
    public Solution calculateCrossProduct(Matrix matrixA, Matrix matrixB) throws InputMismatchException {

        if (matrixA.getRowCount() > 1 || matrixB.getRowCount() > 1) {
            throw new InputMismatchException("Матрицы должны содержать одну строку");
        }

        if (matrixA.getColumnCount() != matrixB.getColumnCount()) {
            throw new InputMismatchException("Размеры матриц не совпадают");
        }

        if (matrixA.getColumnCount() != 3) {
            throw new InputMismatchException("Операция выполнима только для матриц размером 1x3");
        }

        Solution solution = new Solution();
        solution.matrix = new Matrix(1, 3);

        double a0 = matrixA.getElementAt(0, 0);
        double a1 = matrixA.getElementAt(0, 1);
        double a2 = matrixA.getElementAt(0, 2);
        double b0 = matrixB.getElementAt(0, 0);
        double b1 = matrixB.getElementAt(0, 1);
        double b2 = matrixB.getElementAt(0, 2);

        solution.matrix.setElementAt(0, 0, a1 * b2 - a2 * b1);

        solution.matrix.setElementAt(0, 1, a2 * b0 - a0 * b2);

        solution.matrix.setElementAt(0, 2, a0 * b1 - a1 * b0);

        solution.text = String.format("[(%f * %f) - (%f * %f)  (%f * %f) - (%f * %f)  (%f * %f) - (%f * %f)]",
                a1, b2, a2, b1, a2, b0, a0, b2, a0, b1, a1, b0);
        solution.text += " = " + solution.matrix.toString();
        return solution;
    }

    /**
     * Вычисляет произведение двух матриц.
     *
     * @param matrixA первая матрица размером MxK
     * @param matrixB вторая матрица размером KxN
     * @return объект Solution, содержащий текстовое описание решения и матрицу размером MxN,
     * которая представляет произведение двух матриц
     * @throws InputMismatchException если количество столбцов в матрице A не равно количеству строк в матрице B
     */
    public Solution calculateProduct(Matrix matrixA, Matrix matrixB) throws InputMismatchException {
        // Перемножение матриц размером (M,K) x (K,N) = (M,N)

        int M = matrixA.getRowCount();
        int N = matrixB.getColumnCount();
        int K = matrixB.getRowCount();

        if (matrixA.getColumnCount() != matrixB.getRowCount()) {
            throw new InputMismatchException(
                    "Количество столбцов в матрице A должно быть равно количеству строк в матрице B");
        }

        Solution solution = new Solution();
        solution.matrix = new Matrix(M, N);

        StringJoiner rowJoiner = new StringJoiner("\n");

        for (int i = 0; i < M; i++) {
            StringJoiner colJoiner = new StringJoiner("\t");

            for (int j = 0; j < N; j++) {

                StringJoiner sumJoiner = new StringJoiner(" + ");
                double sum = 0;

                for (int k = 0; k < K; k++) {
                    double a = matrixA.getElementAt(i, k);
                    double b = matrixB.getElementAt(k, j);
                    sum += a * b;
                    sumJoiner.add(String.format("(%f * %f)", a, b));
                }

                colJoiner.add(sumJoiner.toString());
                solution.matrix.setElementAt(i, j, sum);
            }
            rowJoiner.add(colJoiner.toString());
        }

        solution.text = rowJoiner.toString();
        solution.text += "\n=\n" + solution.matrix.toString();

        return solution;
    }


}
