package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Vector;

import back.Matrix;
import back.Solver;

public class MainForm extends JFrame {
    private JPanel panelMain;
    private JTable tableA;
    private JTable tableB;
    private JTable tableC;
    private JComboBox operationType;
    private JTextArea solutionText;
    private JTextField matrixARows;
    private JTextField matrixACols;
    private JButton matrixAInit;
    private JButton matrixBInit;
    private JButton operationButton;
    private JLabel operationLabel;
    private JLabel matrixALabel;
    private JLabel matrixBLabel;
    private JLabel matrixCLabel;
    private JTextField matrixBRows;
    private JTextField matrixBCols;
    private JLabel matrixCDimsLabel;

    void drawMatrix(JTable table, Matrix matrix) {
        table.clearSelection();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.setRowCount(0);
        model.setColumnCount(0);

        model.setRowCount(matrix.getRowCount());
        model.setColumnCount(matrix.getColumnCount());

        for (int row = 0; row < matrix.getRowCount(); row++) {
            for (int col = 0; col < matrix.getColumnCount(); col++) {
                double value = matrix.getElementAt(row, col);
                model.setValueAt(value, row, col);
            }
        }
    }

    int[] getMatrixDims(JTextField rowsTextField, JTextField colsTextField) throws NumberFormatException {
        int rows = 0, cols = 0;
        rows = Integer.parseInt(rowsTextField.getText());
        cols = Integer.parseInt(colsTextField.getText());
        return new int[]{rows, cols};
    }

    public Matrix parseMatrix(JTable table) throws NumberFormatException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Matrix parsed = new Matrix(model.getRowCount(), model.getColumnCount());

        int row = 0;
        int col = 0;
        try {
            for (row = 0; row < parsed.getRowCount(); row++) {
                for (col = 0; col < parsed.getColumnCount(); col++) {
                    String cellData = model.getValueAt(row, col).toString();
                    double value = Double.parseDouble(cellData);
                    parsed.setElementAt(row, col, value);
                }
            }
        } catch (NumberFormatException e) {
            String message = String.format(
                    "Неудалось преобразовать элемент (%d строка ,%d столбец ) к числу", row + 1, col + 1);
            throw new NumberFormatException(message);
        }

        return parsed;
    }

    public MainForm() {

        matrixAInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] dims;

                try {
                    dims = getMatrixDims(matrixARows, matrixACols);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(matrixAInit,
                            "Количество строк и столбцов матрицы A должно быть числом",
                            "Неверный размер матрицы", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int rowsCount = dims[0], colsCount = dims[1];
                if (rowsCount < 0 || colsCount < 0) {
                    JOptionPane.showMessageDialog(matrixAInit,
                            "Количество строк и столбцов матрицы A должно быть положительным",
                            "Неверный размер матрицы", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                drawMatrix(tableA, new Matrix(rowsCount, colsCount));
            }
        });

        matrixBInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] dims;

                try {
                    dims = getMatrixDims(matrixBRows, matrixBCols);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(matrixBInit,
                            "Количество строк и столбцов матрицы B должно быть числом",
                            "Неверный размер матрицы", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int rowsCount = dims[0], colsCount = dims[1];
                if (rowsCount < 0 || colsCount < 0) {
                    JOptionPane.showMessageDialog(matrixBInit,
                            "Количество строк и столбцов матрицы B должно быть положительным",
                            "Неверный размер матрицы", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                drawMatrix(tableB, new Matrix(rowsCount, colsCount));
            }
        });

        operationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Matrix matrixA, matrixB;

                try {
                    matrixA = parseMatrix(tableA);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(operationButton,
                            ex.getMessage(),
                            "Ошибка в матрице A", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    matrixB = parseMatrix(tableB);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(operationButton,
                            ex.getMessage(),
                            "Ошибка в матрице B", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Solver.Solution solution;
                Solver solver = new Solver();

                try {
                    switch (operationType.getSelectedIndex()) {
                        case 0: // cross
                            solution = solver.calculateCrossProduct(matrixA, matrixB);
                            break;
                        case 1: // dot
                            solution = solver.calculateDotProduct(matrixA, matrixB);
                            break;
                        case 2: // prod
                            solution = solver.calculateProduct(matrixA, matrixB);
                            break;
                        default:
                            throw new InputMismatchException("Неизвестная операция");
                    }
                } catch (InputMismatchException ex) {
                    JOptionPane.showMessageDialog(operationButton, ex.getMessage(),
                            "Ошибка при выполнении операции", JOptionPane.ERROR_MESSAGE );
                    return;
                }

                Matrix solutionMatrix = solution.getMatrix();
                String dimsLabel = String.format("%d x %d",
                        solutionMatrix.getRowCount(), solutionMatrix.getColumnCount());

                drawMatrix(tableC, solutionMatrix);
                matrixCDimsLabel.setText(dimsLabel);
                solutionText.setText(solution.getText());
            }
        });
    }

    public static void main(String[] args) {
        MainForm form = new MainForm();
        form.setContentPane(form.panelMain);
        form.setTitle("Матрицы");
        form.setSize(800, 600);

        form.drawMatrix(form.tableC, new Matrix(1, 1));

        form.setVisible(true);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
