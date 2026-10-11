package PracticalFour;

public class Main {
    public static void main(String[] args) {

        taskOne(3);

        System.out.println("");
        taskTwo(5, 2);
        System.out.println("");
        taskTwo(2, 5);

        System.out.println("");
        System.out.println(taskThree(1));
        System.out.println(taskThree(2));
        System.out.println(taskThree(4));
        System.out.println(taskThree(8));
        System.out.println(taskThree(16));
        System.out.println(taskThree(32));
        System.out.println(taskThree(64));
        System.out.println(taskThree(128));
        System.out.println(taskThree(256));
        System.out.println(taskThree(512));
        System.out.println(taskThree(1024));
        System.out.println(taskThree(6));
        System.out.println(taskThree(5));
        System.out.println(taskThree(3));
        System.out.println(taskThree(1));

        System.out.println("");
        System.out.println(taskFour(179));

        taskFive(179);

        System.out.println();
        taskSix(179);
    }

    static void taskOne(int n) {
        if (n == 0) {
            return;
        }
        taskOne(n - 1);
        System.out.print(n + " ");
    }

    static void taskTwo(int num1, int num2) {
        System.out.print(num1);
        System.out.println(" ");
        if (num1 == num2) {
            return;
        }
        if (num1 < num2) {
            taskTwo(num1 + 1, num2);
        } else {
            taskTwo(num1 - 1, num2);
        }
    }

    static String taskThree(int num) {
        if (num == 1) {
            return "YES";
        }
        if (num % 2 != 0) {
            return "NO";
        }
        return taskThree(num / 2);
    }


    static int taskFour(int num) {
        if (num == 0) {
            return 0;
        }
        return num % 10 + taskFour(num / 10);
    }


    static void taskFive(int n) {
        if (n == 0) {
            return;
        }
        System.out.print(n % 10);
        taskFive(n / 10);
    }

    static void taskSix(int n) {
        if (n == 0) {
            return;
        }
        taskSix(n / 10);
        System.out.print(n % 10);
        System.out.print(" ");
    }
}
