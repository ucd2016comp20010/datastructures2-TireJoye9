package project20280.list;

import project20280.interfaces.List;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E value, Node<E> previous, Node<E> nextNode) {
            data = value;
            prev = previous;
            next = nextNode;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        //changed to just pointers, dont see a point of having a dummy node in list, just makes all functions account for a ghost node
        head = null;
        tail = null;
        size = 0;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
        if (pred == null || succ == null)
        {
            throw new IllegalArgumentException("Cannot insert when either is null");
        }
        Node<E> newNode = new Node<E>(e,pred,succ);
        pred.next = newNode;
        succ.prev = newNode;
        size++;
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
    public boolean contains(Object o) {
        Node<E> temp = head;
        while (temp.next != null) {
            if (temp.equals(o)) return true;
            temp = temp.next;
        }
        return false;
    }

    @Override
    public E get(int position) {
        if (head == null) return null;
        Node<E> temp = head;
        for(int i = 0; i < position; i++)
        {
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public E set(int index, E value) {
        if (index < 0 && index > size) throw new IllegalArgumentException("Index is out of order");
        this.get(index);//would return the value
        Node<E> temp = head;
        for(int i = 0; i < index; i++)
        {
            temp = temp.next;
        }
        temp.data = value;
        return temp.data;
    }

    @Override
    public void add(int position, E e) {
        Node<E> newNode = new Node<E>(e,null,null);
        if (position < 0 && position > size) throw new IllegalArgumentException("Weird Position");
        if (head == null) {
            head = newNode;
            return;
        }
        if (position == 0) this.addFirst(e);
        if (position == size && position != 0) this.addLast(e);
        else {
            Node<E> temp = head;
            Node<E> hold = null;
            for(int i = 0; i < position - 1; i++)
            {
                temp = temp.next;
            }
            hold = temp.next;
            temp.next = newNode;
            newNode.prev = temp;
            newNode.next = hold;
            hold.prev = newNode;
        }
        size++;
    }

    @Override
    public E remove(int position) {
        if (head == null) return null;
        if (position == 0) this.removeLast();
        if (position == size && position != 0) this.removeLast();
        Node<E> temp = head;
        for (int i = 0; i < position -1;i++)
        {
            temp = temp.next;
        }
        E removedValue = temp.next.data;
        temp.next = temp.next.next;
        temp.next.next.prev = temp.next;
        size--;
        return removedValue;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> temp = head;
        int index = 0;
        while (temp.next != null)
        {
            if (temp.equals(o)) return index;
            temp = temp.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> temp = tail;
        int index = 0;
        while (temp.prev != null)
        {
            if (temp.equals(o)) return index;
            temp = temp.prev;
            index++;
        }
        return -1;
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

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
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
        return new DoublyLinkedListIterator<E>();
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

    private E remove(Node<E> n) {
        E removedValue;
        if (n.equals(head)) return this.removeFirst();
        if (n.equals(tail)) return this.removeLast();
        if (head == null)return null;

        Node<E> temp = head;
        while (temp.next != null)
        {
            if (temp.data == n.data)
            {
                removedValue = temp.next.data;
                temp.next = temp.next.next;
                temp.next.next.prev = temp.next;
                size--;
                return removedValue;
            }
            temp = temp.next;
        }
        return null;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getData();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getData();
    }

    @Override
    public E removeFirst() {
        E removedValue;
        if (head == null) return null;
        if (size == 1)
        {
            removedValue = head.data;
            head = null;
            tail = null;
            size--;
            return removedValue;

        }
        removedValue = head.data;
        head = head.next;
        head.prev = null;
        size--;
        return removedValue;
    }

    @Override
    public E removeLast() {
        if (head == null) return null;
        E removedValue = tail.data;
        tail = tail.prev;
        tail.next = null;
        size--;
        return removedValue;
    }

    @Override
    public void addLast(E e) {
    Node<E> newNode = new Node<E>(e,null,null);
    if (head == null) {
        head = newNode;
        tail = head;
    }
    else {
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }
    size++;
    }

    @Override
    public void addFirst(E e) {
    Node<E> newNode = new Node<>(e, null, null);
    if (head == null)
    {
        head = newNode ;
        tail = newNode;
    }
    else {
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }
    size++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        System.out.println(ll);
        ll.addFirst(1);
        System.out.println(ll);
        ll.addFirst(2);
        System.out.println(ll);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}