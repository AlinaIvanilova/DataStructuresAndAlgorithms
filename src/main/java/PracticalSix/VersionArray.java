package PracticalSix;

import java.util.Arrays;

public class VersionArray<T> {
    static void main() {
        VersionArray<Integer> stack = new VersionArray<>(5);

        stack.push(111);
        stack.push(221);
        stack.push(331);


        VersionArray<String> stackS = new VersionArray<>(5);

        stackS.push("111a");
        stackS.push("221s");
        stackS.push("331c");

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


        System.out.println("Вершина: " + stackS.peek());
        System.out.println("Стек після peek: " + stackS);

        System.out.println("Видалено: " + stackS.pop());
        System.out.println("Стек після pop: " + stackS);

        System.out.println("count = " + stackS.count());
        System.out.println("size = " + stackS.size());

        stackS.clear();
        System.out.println("Стек після clear: " + stackS);
        System.out.println("size = " + stackS.size());


//        try {
//            stack.pop();
//        } catch (RuntimeException e) {
//            System.out.println("Помилка: " + e.getMessage());
//        }
    }

    private final Object[] data;
    private int top;

    public VersionArray(int capacity) {
        data = new Object[capacity];
        //Якщо елементу немає то -1
        top = -1;
    }

    //додає елемент на вершину
    public void push(T value) {
        if (top == data.length - 1) {
            throw new RuntimeException("стек переповнений");
        }
        top++;
        data[top] = value;
    }

    //повертає елемент з вершини і видалає

    @SuppressWarnings("unchecked")
    public T pop() {
        if (top == -1) {
            throw new RuntimeException("стек порожній");
        }
        T value = (T) data[top];
        top--;
        return value;
    }

    // повертає елемент з верху але не видаляє
    @SuppressWarnings("unchecked")
    public T peek() {
        if (top == -1) throw new RuntimeException("стек порожній");
        return (T) data[top];
    }

    public void clear() {
        top = -1;
    }

    // кількість елем
    public int count() {
        return top + 1;
    }

    public int size() {
        return data.length;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    // рядкове подання, елементи не видаляються
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, top + 1));
    }
}