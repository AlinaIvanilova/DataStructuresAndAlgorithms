package org.example;

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
            if (value > 0) {
                positives[pi++] = value;
            } else if (value < 0) {
                negatives[ni++] = value;
            }
        }
        return new int[][]{positives, negatives};
    }
}