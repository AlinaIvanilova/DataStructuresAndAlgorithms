//package PracticalEight;
//
//public class BinaryTree {
//    public static void main(String[] argst) {
//        BinaryTree tree = new BinaryTree();
//
//    }
//
//    private static class Node {
//        int data; // значення
//        Node left; // менші значення
//        Node right; // більші або рівні значення
//
//        Node(int data) {
//            data = value;
//            left = null;
//            right = null;
//        }
//    }
//
//    // корінь дерева (null = дерево порожнє)
//    private Node root;
//    private int count;
//
//    public BinaryTree() {
//        root = null;
//        count = 0;
//    }
//
//    public void add(int value) {
//        Node node = new Node(value);
//        if (root == null) {
//            // дерево порожнє тому елемент стає коренем
//            root = node;
//        } else {
//            Node current = root;
//            while (true) {
//                if (value < current.data) {
//                    // якщо число менше тоді ліворуч
//                    if (current.left == null) {
//                        current.left = node;
//                        break;
//                    }
//                    current = current.left;
//                } else {
//                    // якщо більше чи рівне - направо
//                    if (current.right == null) {
//                        current.right = node;
//                        break;
//                    }
//                    current = current.right;
//                }
//            }
//        }
//        count++;
//    }
//
//    // перевірка чи елемент у дереві
//    public boolean contains(int value) {
//        Node current = root;
//        while (current != null) {
//            if (value == current.data) {
//                return true;
//            } else if (value < current.data) {
//                current = current.left; // менше - шукаємо ліворуч
//            } else {
//                current = current.right; // більше - шукаємо праворуч
//            }
//        }
//        return false;
//    }
//
//
//}
