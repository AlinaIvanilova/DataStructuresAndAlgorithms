package PracticalThree;

import java.util.Random;

/*
До простих алгоритмів сортування відносяться: Bubble sort, Insertion sort
та Selection sort.
Для початку, вам необхідно створити метод, що генерує масив необхідної
довжини в заданому діапазоні та метод що виводить елементи масиву в
наступному вигляді:
"{[cell - 0,value - 4],[cell - 2,value - 10]. . .[cell - n,value - n]}"
Далі вам необхідно реалізувати методи сортування у вигляді методів для
сортування цілочисельних масивів різної довжини, як в прямому(від меншого до
більшого) так і в зворотньому порядку(від більшого до меншого).
Методи сортування повинен приймати: несортований масив, та вказівник.
Якщо вказівник true - сортування відбувається в прямому порядку, false - в
зворотньому порядку.
 */
public class Main {
    public static int[] generateArray(int length, int min, int max) {
        Random rand = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = rand.nextInt(max - min + 1) + min;
        }
        return arr;
    }

    public static void printArray(int[] array) {
//        System.out.print("{");
//        for (int i = 0; i < array.length; i++) {
//            System.out.print("[cell - "+ i + ",value - "+ array[i] + "]");
//            if (i < array.length - 1) {
//                System.out.print(",");
//            }
//        }
//        System.out.print("}");
//        System.out.println();
    }

    public static boolean isSorted(int[] array, boolean ascending) {
        for (int i = 0; i < array.length - 1; i++) {
            if (!inOrder(array[i], array[i + 1], ascending)) {
                return false;
            }
        }
        return true;
    }

    private static boolean inOrder(int a, int b, boolean ascending) {
        return ascending ? a <= b : a >= b;
    }
    public static void main(String[] args) {
        int[] original = generateArray(1_000_000, -10000000, 10000000);
        System.out.println("Original array:");
        printArray(original);

        // Bubble sort
        int[] a = original.clone();
        long  t1 =System.currentTimeMillis();
        bubbleSort(a, true);
        long  t2 =System.currentTimeMillis();
        System.out.println("Bubble Sort time: " + (t2 - t1) + "ms");
        System.out.println("\nBubble sort (за зростанням):");
        printArray(a);
        a = original.clone();
        System.out.println("Відсортовано: " + isSorted(a, true));

        bubbleSort(a, false);
        System.out.println("Bubble sort (за спаданням):");
        printArray(a);
        System.out.println("Відсортовано:  " + isSorted(a, false));

        // Insertion sort
        int[] b = original.clone();
        insertionSort(b, true);
        System.out.println("\nInsertion sort (за зростанням):");
        printArray(b);
        System.out.println("Відсортовано: " + isSorted(b, true));
        b = original.clone();
        insertionSort(b, false);
        System.out.println("Insertion sort (за спаданням):");
        printArray(b);
        System.out.println("Відсортовано: " + isSorted(b, false));

        // Selection sort
        int[] c = original.clone();
        selectionSort(c, true);
        System.out.println("\nSelection sort (за зростанням):");
        printArray(c);
        System.out.println("Відсортовано: "  + isSorted(c, true));
        c = original.clone();
        selectionSort(c, false);
        System.out.println("Selection sort (за спаданням):");
        printArray(c);
        System.out.println("Відсортовано: " + isSorted(c, false));
    }

    public static void bubbleSort(int[] array, boolean ascending) {
        int n = array.length;
        boolean swapped = true;
        for (int pass = 0; pass < n - 1  && swapped; pass++) {
            swapped = false;
            for (int j = 0; j < n - 1 - pass; j++) {
                if (!inOrder(array[j], array[j + 1], ascending)) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

    public static void insertionSort(int[] array, boolean ascending) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && !inOrder(array[j], key, ascending)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void selectionSort(int[] array, boolean ascending) {
        for (int i = 0; i < array.length - 1; i++) {
            int best = i;
            for (int j = i + 1; j < array.length; j++) {
                if (!inOrder(array[best], array[j], ascending)) {
                    best = j;
                }
            }
            if (best != i) {
                int temp = array[i];
                array[i] = array[best];
                array[best] = temp;
            }
        }
    }
}
