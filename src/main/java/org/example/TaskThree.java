package org.example;

public class TaskThree {

    // 3. Третій масив як попарна сума елементів двох масивів однакової довжини
    public static int[] task3(int[] a, int[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Масиви повинні мати однакову довжину");
        }
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }
}