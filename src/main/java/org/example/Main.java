package org.example;

public class Main {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateArray(10, -10, 10);
        ArrayUtils.printArray("Мій масив", arr);

        TaskOne.task1(arr, -5, 10);
        TaskTwo.task2(arr);

        int[] a = ArrayUtils.generateArray(5, -10, 10);
        int[] b = ArrayUtils.generateArray(5, -10, 10);
        ArrayUtils.printArray("Масив A", a);
        ArrayUtils.printArray("Масив B", b);
        int[] sumArr = TaskThree.task3(a, b);
        ArrayUtils.printArray("Результат (A[i] + B[i])", sumArr);

        int[] a4 = ArrayUtils.generateArray(4, 0, 9);
        int[] b4 = ArrayUtils.generateArray(7, 0, 9);
        ArrayUtils.printArray("Масив A4", a4);
        ArrayUtils.printArray("Масив B4", b4);
        int[] concatArr = TaskFour.task4(a4, b4);
        ArrayUtils.printArray("Конкатенація A4 + B4", concatArr);

        int[] arr5 = ArrayUtils.generateArray(8, -15, 15);
        ArrayUtils.printArray("До обміну", arr5);
        TaskFive.task5(arr5);
        ArrayUtils.printArray("Після обміну max і min", arr5);

        int[] arr6 = ArrayUtils.generateArray(10, -10, 10);
        ArrayUtils.printArray("Завдання 6. Вихідний масив", arr6);
        int[][] split = TaskSix.task6(arr6);
        ArrayUtils.printArray("  Додатні елементи", split[0]);
        ArrayUtils.printArray("  Від'ємні елементи", split[1]);

        int[] arr7 = {5, 2, 8, 8, 1, 2, 8, 1, 9, 2};
        ArrayUtils.printArray("Завдання 7. Вихідний масив", arr7);
        int[] noDup = TaskSeven.task7(arr7);
        ArrayUtils.printArray("  Після видалення дублікатів max/min", noDup);

        int[] a8 = ArrayUtils.generateArray(6, -10, 20);
        int[] b8 = ArrayUtils.generateArray(6, -10, 20);
        ArrayUtils.printArray("Масив A8", a8);
        ArrayUtils.printArray("Масив B8", b8);
        int[] between = TaskEight.task8(a8, b8);
        ArrayUtils.printArray("Завдання 8. Елементи між середніми", between);
    }
}