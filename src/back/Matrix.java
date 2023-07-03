package back;
import java.util.StringJoiner;

public class Matrix {
    private int rowCount;
    private int columnCount;
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

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public double[][] getElement() {
        return element;
    }

    public double getElementAt(int row, int col){

        if (row >= this.rowCount || col >= this.columnCount) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        return element[row][col];
    }

    public void setElementAt(int row, int col, double value) {

        if (row >= this.rowCount || col >= this.columnCount) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        element[row][col] = value;
    }

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
