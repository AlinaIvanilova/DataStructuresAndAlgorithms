package org.example;

public class TaskSeven {

    // 7. Видалити з масиву дублікати максимуму та мінімуму
    public static int[] task7(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        int max = arr[0];
        int min = arr[0];
        for (int value : arr) {
            if (value > max) max = value;
            if (value < min) min = value;
        }

        int[] temp = new int[arr.length];
        int size = 0;
        boolean maxUsed = false;
        boolean minUsed = false;

        for (int value : arr) {
            if (value == max && max != min) {
                if (maxUsed) continue;
                maxUsed = true;
            } else if (value == min) {
                if (minUsed) continue;
                minUsed = true;
            }
            temp[size++] = value;
        }

        int[] result = new int[size];
        System.arraycopy(temp, 0, result, 0, size);
        return result;
    }
}