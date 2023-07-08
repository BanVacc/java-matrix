package back;

import java.util.StringJoiner;

public class Matrix {
    private final int rowCount;
    private final int columnCount;
    private final double[][] element;

    public Matrix(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.element = new double[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                element[i][j] = 0;
            }
        }
    }

    /**
     * Возвращает число строк в матрице
     *
     * @return число строк в матрице
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Возвращает число столбцов в матрице
     *
     * @return число столбцов в матрице
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * Возвращает элемент матрицы по указанным индексам
     *
     * @param row индекс строки элемента
     * @param col индекс столбца элемента
     * @return значение элемента матрицы по указанным индексам
     * @throws IndexOutOfBoundsException если индексы выходят за пределы размеров матрицы
     */
    public double getElementAt(int row, int col) throws IndexOutOfBoundsException {

        if (row >= this.rowCount || col >= this.columnCount) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        return element[row][col];
    }

    /**
     * Устанавливает значение элемента по указанным индексам
     *
     * @param row   индекс строки элемента
     * @param col   индекс столбца элемента
     * @param value значение, которое будет установлено
     * @throws IndexOutOfBoundsException если индексы выходят за пределы размеров матрицы
     */
    public void setElementAt(int row, int col, double value) throws IndexOutOfBoundsException {

        if (row >= this.rowCount || col >= this.columnCount) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        element[row][col] = value;
    }

    /**
     * Возвращает строковое представление матрицы.
     *
     * @return строковое представление матрицы, где каждая строка представлена в отдельной строке,
     * а элементы разделены пробелами
     */
    public String toString() {
        StringJoiner rowJoiner = new StringJoiner("\n");

        for (int row = 0; row < rowCount; row++) {
            StringJoiner colJoiner = new StringJoiner(" ");

            for (int col = 0; col < columnCount; col++) {
                colJoiner.add(Double.toString(element[row][col]));
            }
            rowJoiner.add(colJoiner.toString());
        }

        return rowJoiner.toString();
    }

}
