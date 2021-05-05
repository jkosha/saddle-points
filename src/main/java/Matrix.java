import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Matrix {

    private final int[][] values;

    Matrix(List<List<Integer>> values) {
        this.values = convert2DListToMatrix(values);
    }

    /**
     * The matrix is *assumed* to be either empty or filled with Integers
     * Therefore validation has not been added
     *
     * @return
     */
    Set<MatrixCoordinate> getSaddlePoints() {
        if (null == values || 0 == values.length || 0 == values[0].length) {
            return Collections.emptySet();
        }

        Set<MatrixCoordinate> result = new HashSet<>();

        for (int rowIndex = 0; rowIndex < values.length; rowIndex++) {
            int rowMax = Arrays.stream(values[rowIndex]).max().orElse(0);

            for (int colIndex = 0; colIndex < values[rowIndex].length; colIndex++) {
                List<Integer> columnValues = getColumnIntegers(values, colIndex);
                int columnMin = columnValues.stream().min(Integer::compareTo).orElse(0);
                int currentCellValue = values[rowIndex][colIndex];
                if (currentCellValue == rowMax && currentCellValue == columnMin) {
                    result.add(new MatrixCoordinate(rowIndex + 1, colIndex + 1));
                }
            }
        }


        return result;
    }

    private List<Integer> getColumnIntegers(int[][] matrix, int colIndex) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            result.add(matrix[i][colIndex]);
        }
        return result;
    }

    private int[][] convert2DListToMatrix(List<List<Integer>> values) {
        int result[][];
        if ((0 == values.size()) || (0 == values.get(0).size())) {
            result = new int[][]{};
        } else {
            result = new int[values.size()][values.get(0).size()];
            for (int rowIndex = 0; rowIndex < values.size(); rowIndex++) {
                for (int colIndex = 0; colIndex < values.get(rowIndex).size(); colIndex++) {
                    result[rowIndex][colIndex] = values.get(rowIndex).get(colIndex);
                }
            }
        }
        return result;
    }
}
