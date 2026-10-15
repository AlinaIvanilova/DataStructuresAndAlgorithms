package PracticalSix;

public class VersionNode<T> {
     static void main() {
        VersionNode<String> stack = new VersionNode<>();

        stack.push("11");
        stack.push("22");
        stack.push("33");

        VersionNode<Integer> stackInt = new VersionNode<>();

        stackInt.push(11);
        stackInt.push(22);
        stackInt.push(33);

        System.out.println("Стек: " + stack);

        System.out.println("Вершина: " + stack.peek());
        System.out.println("Стек після peek: " + stack);

        System.out.println("Видалено: " + stack.pop());
        System.out.println("Стек після pop: " + stack);

        System.out.println("count = " + stack.count());
        System.out.println("size = " + stack.size());


        stack.clear();
        System.out.println("Стек після clear: " + stack);
        System.out.println("size = " + stack.size());
    }

    private static class  Node<T> {
        T data;
        Node<T> next;

        Node(T value, Node<T> nextNode) {
            data = value;
            next = nextNode;
        }
    }

    private Node<T> top;
    private int count;

    public VersionNode() {
        top = null;
        count = 0;
    }

    public void push(T value) {
        top = new Node<>(value, top);
        count++;
    }

    public T pop() {
        if (top == null) {
            throw new RuntimeException("стек порожній");
        }
        T value = top.data;
        top = top.next;
        count--;
        return value;
    }

    public T peek() {
        if (top == null) {
            throw new RuntimeException("стек порожній");
        }
        return top.data;
    }

    public void clear() {
        top = null;
        count = 0;
    }

    public int size() {
        return count;
    }


    public int count() {
        return count;
    }

    public String toString() {
        if (top == null) return "[]";

        String result = "";
        Node<T> current = top;
        while (current != null) {
            // кожен глибший елемент ставимо перед уже зібраними
            if (result.isEmpty()) {
                result = String.valueOf(current.data);
            } else {
                result = current.data + ", " + result;
            }
            current = current.next;
        }
        return "[" + result + "]";
    }
}