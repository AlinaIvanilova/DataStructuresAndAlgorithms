package org.example;

public class TaskOne {

    // 1. Порахувати кількість та суму парних елементів масиву, що знаходяться в заданому діапазоні.
    public static void task1(int[] arr, int rangeMin, int rangeMax) {
        int count = 0;
        long sum = 0;

        for (int value : arr) {
            if (value % 2 == 0 && value >= rangeMin && value <= rangeMax) {
                count++;
                sum += value;
            }
        }

        System.out.println("Завдання 1. Діапазон [" + rangeMin + "; " + rangeMax + "]");
        System.out.println("  Кількість парних елементів у діапазоні: " + count);
        System.out.println("  Сума парних елементів у діапазоні: " + sum);
    }
}