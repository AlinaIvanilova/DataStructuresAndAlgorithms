package bon;

import java.util.Scanner;

public class TicTacToe {

    static char[] board = new char[9];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            resetBoard();
            boolean quit = playGame(scanner);

            if (quit || !askPlayAgain(scanner)) {
                System.out.println("Дякую за гру. Бувай!");
                break;
            }
        }
    }

    // Очищає поле: клітинки підписані цифрами 1-9
    static void resetBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = (char) ('1' + i);
        }
    }

    // Одна партія. Повертає true, якщо гравець вирішив вийти з програми
    static boolean playGame(Scanner scanner) {
        char player = 'X';

        for (int turn = 0; turn < 9; turn++) {
            printBoard();
            System.out.print("Хід гравця " + player + " (1-9, або q для виходу): ");

            int cell = readCell(scanner);
            if (cell == -1) {
                return true;
            }
            board[cell] = player;

            if (hasWon(player)) {
                printBoard();
                System.out.println("Переміг гравець " + player + "!");
                return false;
            }

            player = (player == 'X') ? 'O' : 'X';
        }

        printBoard();
        System.out.println("Нічия!");
        return false;
    }

    // Питає, чи грати ще. Приймає: т / так / y / yes та н / ні / n / no
    static boolean askPlayAgain(Scanner scanner) {
        System.out.print("Зіграємо ще раз? (т/н): ");
        while (true) {
            String answer = scanner.next().toLowerCase();
            if (answer.equals("т") || answer.equals("так")
                    || answer.equals("y") || answer.equals("yes")) {
                return true;
            }
            if (answer.equals("н") || answer.equals("ні")
                    || answer.equals("n") || answer.equals("no")) {
                return false;
            }
            System.out.print("Введи т (так) або н (ні): ");
        }
    }

    // Читає номер клітинки, поки гравець не введе коректне значення
    static int readCell(Scanner scanner) {
        while (true) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("exit")
                    || input.equalsIgnoreCase("вихід")) {
                return -1;
            }
            if (input.length() == 1 && input.charAt(0) >= '1' && input.charAt(0) <= '9') {
                int cell = input.charAt(0) - '1';
                if (board[cell] != 'X' && board[cell] != 'O') {
                    return cell;
                }
                System.out.print("Клітинка зайнята, спробуй іншу: ");
            } else {
                System.out.print("Введи число від 1 до 9 (або q для виходу): ");
            }
        }
    }

    static void printBoard() {
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.println(" " + board[i] + " | " + board[i + 1] + " | " + board[i + 2]);
            if (i < 6) System.out.println("---+---+---");
        }
        System.out.println();
    }

    static boolean hasWon(char p) {
        int[][] lines = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},   // рядки
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},   // стовпці
                {0, 4, 8}, {2, 4, 6}               // діагоналі
        };
        for (int[] l : lines) {
            if (board[l[0]] == p && board[l[1]] == p && board[l[2]] == p) {
                return true;
            }
        }
        return false;
    }
}