package PracticalSeven;

public class QueueNode<T> {
    static void main() {
        QueueNode<Integer> queue = new QueueNode<>();

        queue.enqueue(11);
        queue.enqueue(22);
        queue.enqueue(33);
        IO.println("Черга: " + queue);

        IO.println("Перший: " + queue.peek());
        IO.println("Черга після peek: " + queue);

        IO.println("Видалено: " + queue.dequeue());
        IO.println("Черга після dequeue: " + queue);

        IO.println("count = " + queue.count());

        queue.clear();
        IO.println("Черга після clear: " + queue);
        IO.println("count = " + queue.count());
    }

    // зберігає значення і посилання на наступний вузол
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T value ) {
            data = value;
            next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int count;

    public QueueNode() {
        head = null;
        tail = null;
        count = 0;
    }

    // додає елемент у кінец черги
    public void enqueue(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        count++;
    }

    //повертає перший і видаляє
    public T dequeue() {
        if (head == null) {
            throw new RuntimeException("черга порожня");
        }
        T value = head.data;
        head = head.next;

        if (head == null) {
            tail = null;
        }
        count--;
        return value;
    }

    //повертає перший не видаляючи
    public T peek() {
        if (head == null) {
            throw new RuntimeException("черга порожня");
        }
        return head.data;
    }

    //чистка
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    public int count() {
        return count;
    }


    public String toString() {
        String result = "[";
        Node<T> current = head;
        while (current != null) {
            result = result + current.data;
            if (current.next != null) {  // кома якщо далі є ще елемент
                result = result + ", ";
            }
            current = current.next;
        }
        return result + "]";
    }
}