import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static ComplexMatrix a = null;
    static ComplexMatrix b = null;

    public static void main(String[] args) {
        boolean work = true;
        while (work) {
            System.out.println();
            System.out.println("1 - ввести матрицу A");
            System.out.println("2 - ввести матрицу B");
            System.out.println("3 - показать A и B");
            System.out.println("4 - A + B");
            System.out.println("5 - A * B");
            System.out.println("6 - A / B");
            System.out.println("7 - транспонировать A");
            System.out.println("8 - определитель A");
            System.out.println("0 - выход");
            System.out.print("Выберите пункт: ");
            String choice = in.nextLine();

            try {
                if (choice.equals("1")) {
                    a = readMatrix();
                } else if (choice.equals("2")) {
                    b = readMatrix();
                } else if (choice.equals("3")) {
                    System.out.println("A:");
                    System.out.println(a);
                    System.out.println("B:");
                    System.out.println(b);
                } else if (choice.equals("4")) {
                    checkBoth();
                    System.out.println(a.add(b));
                } else if (choice.equals("5")) {
                    checkBoth();
                    System.out.println(a.multiply(b));
                } else if (choice.equals("6")) {
                    checkBoth();
                    System.out.println(a.divide(b));
                } else if (choice.equals("7")) {
                    checkA();
                    System.out.println(a.transpose());
                } else if (choice.equals("8")) {
                    checkA();
                    System.out.println("Определитель: " + a.determinant());
                } else if (choice.equals("0")) {
                    work = false;
                } else {
                    System.out.println("Нет такого пункта");
                }
            } catch (Exception e) {
                // сюда попадаем при любой ошибке
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    // проверка, что матрица A задана
    static void checkA() {
        if (a == null) {
            throw new IllegalArgumentException("Сначала введите матрицу A!!!");
        }
    }

    // проверка, что заданы обе матрицы
    static void checkBoth() {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Сначала введите обе матрицы!!!");
        }
    }

    // ввод матрицы с клавиатуры
    static ComplexMatrix readMatrix() {
        System.out.print("Число строк: ");
        int rows = Integer.parseInt(in.nextLine().trim());
        System.out.print("Число столбцов: ");
        int cols = Integer.parseInt(in.nextLine().trim());

        ComplexMatrix m = new ComplexMatrix(rows, cols);
        System.out.println("Каждый элемент вводите двумя числами через пробел: действительная мнимая");
        System.out.println("Например, 3+4i вводится как: 3 4");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Элемент [" + (i + 1) + "][" + (j + 1) + "]: ");
                String[] parts = in.nextLine().trim().split(" ");
                double re = Double.parseDouble(parts[0]);
                double im = Double.parseDouble(parts[1]);
                m.data[i][j] = new Complex(re, im);
            }
        }
        return m;
    }
}
