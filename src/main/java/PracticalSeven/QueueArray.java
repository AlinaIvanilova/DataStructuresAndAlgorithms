package PracticalSeven;


/**
 * Безсмертна типізована черга, створена Аліною.
 *
 * @param <T> тип елементів.
 */
public class QueueArray<T> {

    private static int DFAULT_CAPACITY = 10;

    static void main(String[] args){



        QueueArray<Integer> queue = new QueueArray<>();

        queue.enqueue(11);
        queue.enqueue(22);
        queue.enqueue(33);

        queue.enqueue(11);
        queue.enqueue(22);

        System.out.println("Черга: " + queue);

        System.out.println("Перший: " + queue.peek());
        System.out.println("Черга після peek: " + queue);

        System.out.println("Видалено: " + queue.dequeue());
        System.out.println("Черга після dequeue: " + queue);
        System.out.println("Видалено: " + queue.dequeue());
        System.out.println("Черга після dequeue: " + queue);

        queue.enqueue(11);
        queue.enqueue(22);


        System.out.println("Видалено: " + queue.dequeue());
        queue.enqueue(44);
        System.out.println("Черга після dequeue: " + queue);
        System.out.println("Видалено: " + queue.dequeue());
        queue.enqueue(55);
        System.out.println("Черга після dequeue: " + queue);


        System.out.println("count = " + queue.count());
        System.out.println("size = " + queue.size());

        queue.clear();
        System.out.println("Черга після clear: " + queue);
        System.out.println("count = " + queue.count());
    }

    private final Object[] data;
    private int head;
    // tail - індекс вільного значення після останнього
    private int tail;
    private int count;

    public QueueArray(int capacity){
        data = new Object[capacity];
        head = 0;
        tail = 0;
        count = 0;
    }

    public QueueArray() {
        this(DFAULT_CAPACITY);
    }


    /**
     *  Додає елемент у кінець черги.
     *  @param value Значення, до додається.
     */
    public void enqueue(T value){
        if (count == data.length) {
            throw new RuntimeException("черга переповнена");
        }
        data[tail] =  value;
        tail = (tail + 1) % data.length;
        count++;
    }

    /**
     * Вилучає елемент з хвоста черги.
     * @return значення.
     */
    @SuppressWarnings("unchecked")
    public T dequeue(){
        if (count == 0) {
            throw new RuntimeException("черга порожня");
        }
        T value = (T) data[head];
        head = (head + 1) % data.length;
        count--;
        return value;
    }


    /**
     *  повертає перший елемент, не видаляючи.
     * @return Елемент в голові черги.
     */
    @SuppressWarnings("unchecked")
    public T peek(){
        if (count == 0) {
            throw new RuntimeException("черга порожня");
        }
        return (T) data[head];
    }

    public void clear(){
        head = 0;
        tail = 0;
        count = 0;
    }

    public int count(){
        return count;
    }

    public int size(){
        return data.length;
    }

    public String toString(){
        String result = "[";
        for (int i = 0; i < count; i++) {
            result = result + data[(head + i) % data.length];
            if (i < count - 1) {
                result = result + ", ";
            }
        }
        return result + "]";
    }
}
