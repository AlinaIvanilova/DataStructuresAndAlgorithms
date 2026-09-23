package PracticalTwo;

import java.util.Random;

public class Main {
    static Random RND = new Random();

    static double[][] generate(int m, int n, int min, int max) {
        double[][] a = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = min + RND.nextInt(max - min + 1);
        return a;
    }

    static void print(double[][] a) {
        System.out.print("\t");
        for (int j = 0; j < a[0].length; j++)
            System.out.print("стовпець " + (j + 1) + "\t");
        System.out.println();

        for (int i = 0; i < a.length; i++) {
            System.out.print("рядок " + (i + 1) + "\t");
            for (double v : a[i])
                System.out.printf("%.2f\t\t", v);
            System.out.println();
        }
    }
    static void printWithMean(double[][] a, double[] means) {
        System.out.print("\t");
        for (int j = 0; j < a[0].length; j++)
            System.out.print("стовпець " + (j + 1) + "\t");
        System.out.println("середнє");

        for (int i = 0; i < a.length; i++) {
            System.out.print("рядок " + (i + 1) + "\t");
            for (double v : a[i])
                System.out.printf("%.2f\t\t", v);
            System.out.printf("%.2f%n", means[i]);
        }
    }

    public static void main(String[] args) {
        int m = 3, n = 3, k = 1, min = 0, max = 3;

        double[][] a = generate(m, n, min, max);
        System.out.println("Початкова матриця:");
        print(a);


        double[] means = subtractRowMean(a);
        System.out.println("\n1. Після віднімання середнього арифметичного рядка:");
        printWithMean(a, means);

        a = shift(a, k);
        System.out.println("\n2. Після циклічного зсуву на " + k + " вправо та догори:");
        print(a);
        a = removeMax(a);
        System.out.println("\n3. Після видалення рядків і стовпців з максимальними елементами:");
        print(a);

        double[][] sq = generate(n, n, min, max);
        System.out.println("\n4. Квадратна матриця до обертання:");
        print(sq);
        transpose(sq);
        System.out.println("\nПісля транспонування:");
        print(sq);

        reverseRows(sq);
        System.out.println("\nПісля обертання на 90° за годинниковою стрілкою (реверс рядків):");
        print(sq);
    }

    // 1. Віднімання від елементів рядка середнього арифметичного цього рядка
    static double[] subtractRowMean(double[][] a) {
        double[] means = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            double[] row = a[i];
            double sum = 0;
            for (double v : row) sum += v;
            double mean = sum / row.length;
            means[i] = mean;
            for (int j = 0; j < row.length; j++) row[j] -= mean;
        }
        return means;
    }

    // 2. Циклічний зсув на k позицій вправо та на k догори
    static double[][] shift(double[][] a, int k) {
        int m = a.length, n = a[0].length;
        double[][] r = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                r[((i - k) % m + m) % m][((j + k) % n + n) % n] = a[i][j];
        return r;
    }

    // 3. Видалення рядків і стовпців, що містять максимальні елементи
    static double[][] removeMax(double[][] a) {
        int m = a.length, n = a[0].length;
        double max = a[0][0];
        for (double[] row : a)
            for (double v : row) max = Math.max(max, v);

        boolean[] delRow = new boolean[m], delCol = new boolean[n];
        int rows = m, cols = n;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (a[i][j] == max) {
                    if (!delRow[i]) { delRow[i] = true; rows--; }
                    if (!delCol[j]) { delCol[j] = true; cols--; }
                }

        double[][] r = new double[rows][cols];
        for (int i = 0, ri = 0; i < m; i++) {
            if (delRow[i]) continue;
            for (int j = 0, rj = 0; j < n; j++)
                if (!delCol[j]) r[ri][rj++] = a[i][j];
            ri++;
        }
        return r;
    }

    // 4. Обертання квадратної матриці на 90° за годинниковою стрілкою in-place
    static void transpose(double[][] a) {
        int n = a.length;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                double t = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = t;
            }
    }

    static void reverseRows(double[][] a) {
        int n = a.length;
        for (double[] row : a)
            for (int j = 0; j < n / 2; j++) {
                double t = row[j];
                row[j] = row[n - 1 - j];
                row[n - 1 - j] = t;
            }
    }
}
