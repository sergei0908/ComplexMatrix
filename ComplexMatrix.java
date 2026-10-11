public class ComplexMatrix {
    int rows;
    int cols;
    Complex[][] data;

    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new Complex(0, 0);
            }
        }
    }

    // сложение матриц
    public ComplexMatrix add(ComplexMatrix other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Размеры матриц не совпадают!!!");
        }
        ComplexMatrix result = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = data[i][j].add(other.data[i][j]);
            }
        }
        return result;
    }

    // умножение матриц
    public ComplexMatrix multiply(ComplexMatrix other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException(
                    "Нельзя умножить: кол-во столбцов первой не совпадает с кол-вом строк второй матрицы!!!");
        }
        ComplexMatrix result = new ComplexMatrix(rows, other.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                Complex sum = new Complex(0, 0);
                for (int k = 0; k < cols; k++) {
                    sum = sum.add(data[i][k].mul(other.data[k][j]));
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    // транспонирование
    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[j][i] = data[i][j];
            }
        }
        return result;
    }

    // минор
    private ComplexMatrix minor(int row, int col) {
        ComplexMatrix result = new ComplexMatrix(rows - 1, cols - 1);
        int newI = 0;
        for (int i = 0; i < rows; i++) {
            if (i == row) {
                continue;
            }
            int newJ = 0;
            for (int j = 0; j < cols; j++) {
                if (j == col) {
                    continue;
                }
                result.data[newI][newJ] = data[i][j];
                newJ++;
            }
            newI++;
        }
        return result;
    }

    // определитель
    public Complex determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException("Определитель есть только у квадратной матрицы!!!");
        }
        if (rows == 1) {
            return data[0][0];
        }
        Complex result = new Complex(0, 0);
        for (int j = 0; j < cols; j++) {
            Complex term = data[0][j].mul(minor(0, j).determinant()); // расскладываем по строке рекурсивно
            if (j % 2 == 0) {
                result = result.add(term);
            } else {
                result = result.sub(term);
            }
        }
        return result;
    }

    // обратная матрица: (1/det) * транспонированная матрица алгебраических дополнений
    public ComplexMatrix inverse() {
        if (rows != cols) {
            throw new IllegalArgumentException("Обратная матрица есть только у квадратной матрицы!!!");
        }
        Complex det = determinant();
        if (det.isZero()) {
            throw new ArithmeticException("Определитель равен 0, обратной матрицы нет!!!");
        }
        ComplexMatrix result = new ComplexMatrix(rows, cols);
        if (rows == 1) {
            result.data[0][0] = new Complex(1, 0).div(data[0][0]);
            return result;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Complex cofactor = minor(i, j).determinant();
                if ((i + j) % 2 == 1) {
                    cofactor = cofactor.mul(new Complex(-1, 0));
                }
                result.data[j][i] = cofactor.div(det);
            }
        }
        return result;
    }

    // деление матриц: A / B = A * B^-1
    public ComplexMatrix divide(ComplexMatrix other) {
        if (other.rows != other.cols) {
            throw new IllegalArgumentException("Делить можно только на квадратную матрицу!!!");
        }
        return this.multiply(other.inverse());
    }

    // вывод матрицы на экран
    public String toString() {
        String s = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                s = s + data[i][j] + "\t";
            }
            s = s + "\n";
        }
        return s;
    }
}
