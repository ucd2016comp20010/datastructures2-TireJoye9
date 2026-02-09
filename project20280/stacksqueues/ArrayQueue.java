package project20280.stacksqueues;

import project20280.interfaces.Queue;

public class ArrayQueue<E> implements Queue<E> {

    //this is a constant, not a field
    private static final int CAPACITY = 1000;
    //ArrayQueue holds array of any data.
    private E[] data;
    private  int front = 0;
    private  int size = 0;

    //constructor with parameter capacity, do we make an array with that capacity
    //When we create class,array is created, were able to make changes to that array
    //Here they want to declare the size of the array.
    public ArrayQueue(int capacity) {
        data =  (E[]) new Object[capacity];
        size = 0;
        front = 0;
    }

    //Calls other constructor
    public ArrayQueue() {
        this(CAPACITY);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        int i = front;
        while (data[i] != null)
        {
            i +=1;
        }
        data[i] = e;
        size++;
    }

    @Override
    public E first() {
        return isEmpty() ? null : data[front];
    }

    @Override
    public E dequeue() {
        // remove array[0] then shift array back?
        if (isEmpty()) return null;
        E old_data = data[front];
        data[front] = null;
        front += 1;
        size--;
        return old_data;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; ++i) {
            E res = data[(front + i) % CAPACITY];
            sb.append(res);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> qq = new ArrayQueue<>();
        System.out.println(qq);

        int N = 10;
        for (int i = 0; i < N; ++i) {
            qq.enqueue(i);
        }
        System.out.println(qq);

        for (int i = 0; i < N / 2; ++i) qq.dequeue();
        System.out.println(qq);

    }
}
