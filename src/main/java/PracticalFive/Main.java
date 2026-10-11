package PracticalFive;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {1_000, 10_000, 100_000};

        for (int n : sizes) {
            int[] original = generateArray(n, 1000, 0);
            System.out.println("Розмір масиву: " + n);

            int[] arr = original.clone();
            long start = System.nanoTime();
            quickSort(arr, true);
            long end = System.nanoTime();
            System.out.println("QuickSort (зростання): " + (end - start) / 1_000_000.0 + " мс, впорядкований: " + isSorted(arr, true));

            arr = original.clone();
            start = System.nanoTime();
            quickSort(arr, false);
            end = System.nanoTime();
            System.out.println("QuickSort (спадання): " + (end - start) / 1_000_000.0 + " мс, впорядкований: " + isSorted(arr, false));

            arr = original.clone();
            start = System.nanoTime();
            bubbleSort(arr);
            end = System.nanoTime();
            System.out.println("Бульбашка: " + (end - start) / 1_000_000.0 + " мс, впорядкований: " + isSorted(arr, true));

            arr = original.clone();
            start = System.nanoTime();
            selectionSort(arr);
            end = System.nanoTime();
            System.out.println("Вибором: " + (end - start) / 1_000_000.0 + " мс, впорядкований: " + isSorted(arr, true));

            arr = original.clone();
            start = System.nanoTime();
            insertionSort(arr);
            end = System.nanoTime();
            System.out.println("Вставками: " + (end - start) / 1_000_000.0 + " мс, впорядкований: " + isSorted(arr, true));

            System.out.println();
        }
    }

    static int[] generateArray(int lenght, int max, int min) {
        Random random = new Random();
        int[] arr = new int[lenght];
        for (int i = 0; i < lenght; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        return arr;
    }

    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

     static int partition(int[] arr, int left, int right, boolean ascending) {
        int pivot = arr[right]; //еталон або півота
        int i = left;
        for (int j = left; j < right; j++) {
            if (ascending) { //за зростанням
                if (arr[j] < pivot) {
                    swap(arr, i, j);
                    i++;
                }
            } else { //за спаданням
                if (arr[j] > pivot) {
                    swap(arr, i, j);
                    i++;
                }
            }
        }
        swap(arr, i, right);
        return i;
     }

     static void quickSortRec(int[] arr, int left, int right, boolean ascending) {
        if (left < right) {
            int pivot/*індекс*/ = partition(arr, left, right, ascending);
            quickSortRec(arr, left, pivot - 1, ascending);
            quickSortRec(arr, pivot + 1, right, ascending);
        }
     }

     static void quickSort(int[] arr, boolean ascending) {
        quickSortRec(arr, 0, arr.length - 1, ascending);
     }

     static boolean isSorted(int[] arr, boolean ascending) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (ascending) {
                if (arr[i] > arr[i + 1]) {
                    return false;
                }
            } else {
                if (arr[i] < arr[i + 1]) {
                    return false;
                }
            }
        }
         return true;
     }

    static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
