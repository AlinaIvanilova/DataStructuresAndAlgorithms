package PracticalEight;

import java.util.ArrayList;
import java.util.List;

public class BinaryThree<T extends Comparable<T>> {

    static void main() {
        BinaryThree<Integer> tree = new BinaryThree();

        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(12);
        tree.add(20);
        tree.add(50);
        tree.add(30);
        tree.add(40);
        IO.println("Всі елементи: " + tree);
        IO.println("count = " + tree.count());
        tree.printTree();

        IO.println(" ");
        IO.println("Пошук");
        IO.println("Є 7? " + tree.contains(7));
        IO.println("Є 99? " + tree.contains(99));

        IO.println(" ");
        IO.println("Мінімум і максимум");
        IO.println("Найменший: " + tree.minimum());
        IO.println("Найбільший: " + tree.maximum());

        IO.println(" ");
        IO.println("Видалення листка (3)");
        tree.remove(3);
        IO.println("Всі елементи: " + tree);
        IO.println(" ");

        IO.println("count = " + tree.count());
        IO.println("Видалення вузла з однією дитиною (5)");
        tree.remove(5);
        IO.println("Всі елементи: " + tree);
        IO.println(" ");

        IO.println("Видалення вузла з двома дітьми (корінь 10)");
        tree.remove(10);
        IO.println("Всі елементи: " + tree);
        tree.printTree();
        IO.println("count = " + tree.count());

        IO.println(" ");
        IO.println("Видалення неіснуючого (99)");
        IO.println("Видалено? " + tree.remove(99));
        IO.println("count = " + tree.count());

        IO.println(" ");
        BinaryThree<Integer> small = new BinaryThree<>();
        small.add(1);
        small.add(2);

    }

    /**
     * Зберігає значення і посилання на лівого та правого нащадка.
     * @param <T> Тип значення, що зберігається у вузлі.
     */
    private static class Node<T> {
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
    }

    /** Найперший вузол. */
    private Node<T> root;
    /** Кількість елементів. */
    private int count;

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
     * @param result Сюди дописуємо елементи.
     */
    private void collect(Node<T> node, StringBuilder result) {
        // вузла немає - нічого збирати
        if (node == null) {
            return;
        }
        // спочатку ліва частина (менші значення)
        collect(node.left, result);
        // потім сам вузол
        if (result.length() > 0) {  // кома якщо перед цим вже був елемент
            result.append(", ");
        }
        result.append(node.data);
        // в кінці права частина (більші значення)
        collect(node.right, result);
    }

    /** Вивід */
    public String toString() {
        StringBuilder result = new StringBuilder();
        collect(root, result);
        return "[" + result + "]";
    }

    /** Виводить дерево в консоль боком (корінь зліва) */
    public void printTree() {
        if (root == null) {
            IO.println("дерево порожнє");
            return;
        }
        printNode(root, 0);
    }

    /**
     * Виводить вузол з відступом за рівнем.
     * @param node Вузол, який виводимо.
     * @param level На якому рівні стоїть вузол (корінь це 0).
     */
    private void printNode(Node<T> node, int level) {
        // вузла немає - нічого виводити
        if (node == null) {
            return;
        }
        // спочатку права частина, вона буде зверху
        printNode(node.right, level + 1);
        // сам вузол, відступ залежить від рівня
        IO.println("    ".repeat(level) + node.data);
        // потім ліва частина, вона буде знизу
        printNode(node.left, level + 1);
    }

    public void printTreeBeautiful() {
        if (root == null) {
            IO.println("дерево порожнє");
            return;
        }
        printNode(root, 0);
    }

    private List<T> wsf(Node<T> node) {
        int baseWidth = (int) Math.ceil(Math.log10(count) / Math.log10(2));
        int baseSpacing = (baseWidth * 2 - 1) * 3;

        Object[] result = new Object[count];
        result[0] = node;
        node.spacing = baseSpacing / 2 - 1;
//      обхід дерева в ширину
        for (int i = 0, j = 1; i < count; i++) {

        }

    }

}

// розвернути на 90 градусів варік 2