package org.example;

public class TaskTwo {

    // 2. Визначити середнє арифметичне елементів масиву та кількість елементів, більших за середнє.
    public static void task2(int[] arr) {
        if (arr.length == 0) {
            System.out.println("Завдання 2. Масив порожній.");
            return;
        }
        double average = ArrayUtils.average(arr);

        int countAbove = 0;
        for (int value : arr) {
            if (value > average) {
                countAbove++;
            }
        }
        System.out.println("Завдання 2. Середнє арифметичне: " + average);
        System.out.println("  Кількість елементів, більших за середнє: " + countAbove);
    }
}