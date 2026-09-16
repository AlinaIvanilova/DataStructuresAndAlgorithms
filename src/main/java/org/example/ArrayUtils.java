package org.example;

import java.util.Random;

public class ArrayUtils {

    private static final Random RANDOM = new Random();

    public static int[] generateArray(int length, int min, int max) {
        if (length < 0) {
            throw new IllegalArgumentException("Довжина масиву не може бути відємною");
        }
        if (min > max) {
            throw new IllegalArgumentException("Мінімум не може бути більше за максимум");
        }
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = RANDOM.nextInt(max - min + 1) + min;
        }
        return array;
    }

    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append("[елемент_").append(i + 1).append("_значення_").append(arr[i]).append("]");
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static void printArray(String name, int[] arr) {
        System.out.println(name + ": " + arrayToString(arr));
    }

    public static double average(int[] arr) {
        if (arr.length == 0) return 0;
        double sum = 0;
        for (int value : arr) sum += value;
        return sum / arr.length;
    }
}