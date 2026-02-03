package project20280.list;

import project20280.interfaces.List;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T value, Node<T> node) {
            data = value;
            next = node;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
       Node<E> temp = tail;
       if (tail == null) return false;
       for (int i = 0; i < size; i++)
       {
           if (temp.equals(o)) return true;
           temp = temp.next;
       }

       return false;
    }

    @Override
    public E get(int position) {
       if (size == 0) return null;
       Node<E> temp = tail;
       for (int i = 0; i < position + 1; i++)
       {
           temp = temp.next;
       }
        return temp.data;
    }

    @Override
    public E set(int index, E value) {
        if (size == 0) return null;
        Node<E> temp = tail;
        for (int i = 0; i < index; i++)
        {
            temp = temp.next;
        }
        temp.data = value;
        return temp.data;
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param index the index at which the new element should be stored
     * @param value the new element to be stored
     */
    @Override
    public void add(int index, E value) {
        if (index < 0 || index > size) throw new IllegalArgumentException("Incorrect Index");
        if (tail == null) return;
        if (index == 0) this.addFirst(value);
        if (index == size && index != 0) this.addLast(value);
        else {
            Node<E> newNode = new Node<>(value, null);
            Node<E> temp = tail.next;
            Node<E> hold;
            for (int i = 0; i < index -1 ; i++) {
                temp = temp.next;
            }
            hold = temp.next;
            temp.next = newNode;
            newNode.next = hold;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        if (tail == null) return null;
        if (index < 0 || index > size) throw new IllegalArgumentException("This is an incorrect index");
        if (index == 0) removeFirst();
        E removedValue = null;
        if (index == size) removeLast();
        else {
            Node<E> temp = tail.next;
            for (int i = 0; i < size; i++) {
                temp = temp.next;
            }
            removedValue = temp.next.data;
            temp.next = temp.next.next;
        }
        size--;
        return removedValue;
    }

    @Override
    public int indexOf(Object o) {
        if (tail == null) return -1;
        Node<E> temp = tail.next;
        int index = 0;
        for (int i = 0; i < size;i++)
        {
            if (temp.equals(o)) return index;
            temp = temp.next;
            index++;
        }
        return -1;

    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public java.util.List<E> subList(int fromIndex, int toIndex) {
        return java.util.List.of();
    }

    public void rotate() {
        int a = 0;
        do {
            tail = tail.next;
            a++;
        }while (a == 0);
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (size == 1)

        {tail = null  ;
        size--;
        }
        if (tail == null) return null;
        E removedValue = tail.next.data;
        tail.next = tail.next.next;
        size--;
        return removedValue;
    }

    @Override
    public E removeLast() {
        if (tail == null) return null;
        E removedValue;
        Node<E> temp = tail.next;
        if (size == 1)
        {
            removedValue = tail.data;
            tail = null;
        }
        else {
            while (temp.next != tail) {
                temp = temp.next;
            }
            removedValue = temp.next.data;
            tail = temp;
            temp.next = temp.next.next;
        }
        size--;
        return removedValue;
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, null);

        if (tail == null) {
            newNode.next = newNode;
            tail = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, null);

        if (tail == null) {
            newNode.next = newNode;
            tail = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }


    public String toString() {
        if (tail == null) return "[]";

        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail.next;  // Start at head (tail.next)
        int i = 0;

        do {
            sb.append(curr.data);
            curr = curr.next;
            i++;
            if (i < size) {
                sb.append(", ");
            }
        } while (i < size);

        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
