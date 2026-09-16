package org.example;

public class TaskEight {

    // 8. Середні арифметичні двох масивів + третій масив з елементів між середніми
    public static int[] task8(int[] a, int[] b) {
        double avgA = ArrayUtils.average(a);
        double avgB = ArrayUtils.average(b);
        double low = Math.min(avgA, avgB);
        double high = Math.max(avgA, avgB);

        System.out.println("Завдання 8. Середнє масиву A: " + avgA + ", середнє масиву B: " + avgB);

        int[] temp = new int[a.length + b.length];
        int size = 0;
        for (int value : a) {
            if (value >= low && value <= high) {
                temp[size++] = value;
            }
        }
        for (int value : b) {
            if (value >= low && value <= high) {
                temp[size++] = value;
            }
        }
        int[] result = new int[size];
        System.arraycopy(temp, 0, result, 0, size);
        return result;
    }
}