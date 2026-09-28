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
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < array.length; i++) {
            sb.append("[cell - ").append(i).append(",value - ").append(array[i]).append("]");
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        sb.append("}");
        System.out.println(sb);
    }

    private static boolean inOrder(int a, int b, boolean ascending) {
        return ascending ? a <= b : a >= b;
    }
    public static void main(String[] args) {
        int[] original = generateArray(10, -10, 10);
        System.out.println("Original array:");
        printArray(original);

        // Bubble sort
        int[] a = original.clone();
        bubbleSort(a, true);
        System.out.println("\nBubble sort (за зростанням):");
        printArray(a);
        a = original.clone();
        bubbleSort(a, false);
        System.out.println("Bubble sort (за спаданням):");
        printArray(a);

        // Insertion sort
        int[] b = original.clone();
        insertionSort(b, true);
        System.out.println("\nInsertion sort (за зростанням):");
        printArray(b);
        b = original.clone();
        insertionSort(b, false);
        System.out.println("Insertion sort (за спаданням):");
        printArray(b);

        // Selection sort
        int[] c = original.clone();
        selectionSort(c, true);
        System.out.println("\nSelection sort (за зростанням):");
        printArray(c);
        c = original.clone();
        selectionSort(c, false);
        System.out.println("Selection sort (за спаданням):");
        printArray(c);
    }

    public static void bubbleSort(int[] array, boolean ascending) {
        int n = array.length;
        for (int pass = 0; pass < n - 1; pass++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - pass; j++) {
                if (!inOrder(array[j], array[j + 1], ascending)) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
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
