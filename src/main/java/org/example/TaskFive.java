package org.example;

public class TaskFive {

    // 5. В масиві поміняти місцями максимум та мінімум.
    public static void task5(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        int maxIndex = 0;
        int minIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        int temp = arr[maxIndex];
        arr[maxIndex] = arr[minIndex];
        arr[minIndex] = temp;
    }
}