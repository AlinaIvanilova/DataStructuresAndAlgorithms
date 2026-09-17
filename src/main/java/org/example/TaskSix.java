package org.example;

import java.util.Arrays;

public class TaskSix {

    // 6. Поділ масиву на масив додатних та масив від'ємних елементів
    public static int[][] task6(int[] arr) {
        int positiveCount = 0;
        int negativeCount = 0;
        for (int value : arr) {
            if (value > 0) positiveCount++;
            else if (value < 0) negativeCount++;
        }

        int[] positives = new int[positiveCount];
        int[] negatives = new int[negativeCount];
        int pi = 0, ni = 0;
        for (int value : arr) {
            if (value >= 0) {
                positives[pi++] = value;
            } else {
                negatives[ni++] = value;
            }
        }

        return new int[][]{positives, negatives};
    }


    public static int[][] task61(int[] arr) {
        int[] positives = Arrays.stream(arr).filter(value -> value >= 0).toArray();
        int[] negatives = Arrays.stream(arr).filter(value -> value < 0).toArray();
        return new int[][]{positives, negatives};
    }
}