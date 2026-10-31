package PracticalEight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TreeBeautiful<T extends Comparable<T>> {

    static void main() {
        TreeBeautiful<Integer> tree = new TreeBeautiful();

        // корінь = 75 (середина 0..150)
        tree.add(75);
// 2-й рівень
        tree.add(37);
        tree.add(113);
// 3-й рівень
        tree.add(18);
        tree.add(56);
        tree.add(94);
        tree.add(132);
// 4-й рівень
        tree.add(9);
        tree.add(27);
        tree.add(47);
        tree.add(65);
        tree.add(85);
        tree.add(103);
        tree.add(123);
        tree.add(141);
// 5-й рівень
//        tree.add(4);
        tree.add(13);
        tree.add(14);
        tree.add(15);
        tree.add(13);


//        tree.add(22);
//        tree.add(32);
//        tree.add(42);
//        tree.add(52);
        tree.add(60);
        tree.add(61);
        tree.add(62);
        tree.add(70);
        tree.add(80);
        tree.add(90);
        tree.add(98);
        tree.add(108);
        tree.add(118);
        tree.add(128);
        tree.add(136);
        tree.add(146);




        tree.printTreeBeautiful();

        System.out.println(tree);


        tree.remove(94);
        tree.printTreeBeautiful();
    }

    /**
     * Зберігає значення і посилання на лівого та правого нащадка.
     * @param <T> Тип значення, що зберігається у вузлі.
     */
    static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;
        int level;
        int spacing;

        Node(T value) {
            data = value;
            left = null;
            right = null;
        }


        @Override
        public String toString() {
            return  data.toString();
        }
    }



    /** Найперший вузол. */
    Node<T> root;
    /** Кількість елементів. */
    private int count;
    /** Номер колонки для наступного вузла при виводі. */
    private int column;

    /**
     * Дрдає елемент.
     * @param value Значення, яке дає кристувач.
     */
    public void add(T value) {

        Node<T> node = new Node<>(value);
        count++;

        if (root == null) {
            root = node;
            node.level = 0;
            return;
        }

        Node<T> current = root;
        while (true) {
            if (value.compareTo(current.data) < 0) {
                if (current.left == null) {
                    node.level = current.level + 1;
                    current.left = node;
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    node.level = current.level + 1;
                    current.right = node;
                    return;
                }
                current = current.right;
            }
        }
    }

    /**
     * Пошук елемента.
     * @param value Значення, яке шукаєм.
     * @return True - знайшли, False - ні.
     */
    public boolean contains(T value) {
        Node<T> current = root;
        while (current != null) {
            int compare = value.compareTo(current.data);
            if (compare == 0) {
                return true;
            }
            if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    /**
     * Видаляє елемент.
     * @param value Значення яке видалило.
     * @return True - видалили, False - такого значення нема.
     */
    public boolean remove(T value) {
        if (!contains(value)) {
            return false;
        }
        root = removeNode(root, value);
        count--;
        return true;
    }

    /**
     * Шукає вузол і видаляє.
     * @param node Вузол, з якого починаємо пошук.
     * @param value Значення, яке видаляємо.
     * @return Вузол, який має стояти на цьому місці після видалення.
     */
    private Node<T> removeNode(Node<T> node, T value) {
        // дійшли до кінця дерева, видаляти нічого
        if (node == null) {
            return null;
        }

        int compare = value.compareTo(node.data);
        if (compare < 0) {
            // менше - шукаємо ліворуч
            node.left = removeNode(node.left, value);
        } else if (compare > 0) {
            // більше - шукаємо праворуч
            node.right = removeNode(node.right, value);
        } else {
            // знайшли вузол, який треба видалити

            // немає лівої дитини - на його місце йде права (або null)
            if (node.left == null) {
                return node.right;
            }
            // немає правої дитини - на його місце йде ліва
            if (node.right == null) {
                return node.left;
            }

            // є обидві дитини - шукаємо найбільш лівий вузол у правій частині
            Node<T> smallest = node.right;
            while (smallest.left != null) {
                smallest = smallest.left;
            }
            // копіюємо його значення у вузол, який видаляємо
            node.data = smallest.data;
            // видаляємо старий вузол із правої частини
            node.right = removeNode(node.right, smallest.data);
        }
        // повертаємо вузол, щоб батько знав, що стоїть на цьому місці
        return node;
    }

    /**
     * Повертає найменший елемент (найбільш лівий).
     * @return найменше значення.
     */
    public T minimum() {
        if (root == null) {
            throw new RuntimeException("дерево пусте");
        }

        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    /**
     * Повертає найбільший елемент (найбільш правий).
     * @return найбільше значення.
     */
    public T maximum() {
        if (root == null) {
            throw new RuntimeException("дерево пусте");
        }

        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    /** Виведе кількість елементів */
    public int count() {
        return count;
    }

    /**
     * Збирає елементи дерева по зростанню.
     * @param node Вузол, з якого починаємо.
     * @return  result Сюди дописуємо елементи.
     */
    private List<T> collect(Node<T> node) {
        List<T> result = new ArrayList<>(count);

        // вузла немає - нічого збирати
        if (node == null) {
            return result ;
        }


        // спочатку ліва частина (менші значення)
        result.addAll(collect(node.left));
        // потім сам вузол
        result.add(node.data);
        // в кінці права частина (більші значення)
        result.addAll(collect(node.right));
        return result;
    }

    /** Вивід */
    public String toString() {

        List<String> collect = collect(root).stream().map(T::toString).toList();
        return String.join(", ", collect);
    }

    /** Виводить дерево в консоль: корінь зверху, менші ліворуч, більші праворуч. */
    public void printTreeBeautiful() {
        if (root == null) {
            IO.println("дерево порожнє");
            return;
        }
        // рядків: по одному на рівень + по одному для ліній між рівнями
        char[][] grid = new char[height(root) * 2 - 1][count * 4 + 4];
        for (char[] row : grid) {
            java.util.Arrays.fill(row, ' ');
        }
        column = 0;
        fill(root, 0, grid);
        for (char[] row : grid) {
            IO.println(new String(row).stripTrailing());
        }
    }

    /**
     * Малює вузол і лінії до його дітей.
     * Рядок вузла = depth * 2, рядок ліній під ним = depth * 2 + 1.
     * @param node Вузол, який малюємо.
     * @param depth Рівень вузла (корінь це 0).
     * @param grid Полотно, на якому малюємо.
     * @return колонка, в якій стоїть вузол.
     */
    private int fill(Node<T> node, int depth, char[][] grid) {
        int leftCol = -1;
        int rightCol = -1;

        // спочатку ліва частина (менші значення)
        if (node.left != null) {
            leftCol = fill(node.left, depth + 1, grid);
        }

        // сам вузол
        int col = column * 4;
        column++;
        String text = String.valueOf(node.data);
        for (int i = 0; i < text.length(); i++) {
            grid[depth * 2][col + i] = text.charAt(i);
        }

        // в кінці права частина (більші значення)
        if (node.right != null) {
            rightCol = fill(node.right, depth + 1, grid);
        }

        // лінії до дітей
        int row = depth * 2 + 1;
        if (leftCol != -1) {
            grid[row][leftCol] = '┌';
            for (int c = leftCol + 1; c < col; c++) {
                grid[row][c] = '─';
            }
        }
        if (rightCol != -1) {
            grid[row][rightCol] = '┐';
            for (int c = col + 1; c < rightCol; c++) {
                grid[row][c] = '─';
            }
        }
        if (leftCol != -1 && rightCol != -1) {
            grid[row][col] = '┴';
        } else if (leftCol != -1) {
            grid[row][col] = '┘';
        } else if (rightCol != -1) {
            grid[row][col] = '└';
        }

        return col;
    }

    /** Висота дерева (порожнє = 0, один вузол = 1). */
    private int height(Node<T> node) {
        return node == null ? 0 : 1 + Math.max(height(node.left), height(node.right));
    }
}

//балансування 