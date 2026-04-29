package project20280.list;

import com.sun.source.tree.WhileLoopTree;
import project20280.interfaces.List;

import javax.sound.midi.MidiFileFormat;
import java.lang.classfile.instruction.ReturnInstruction;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class SinglyLinkedList<E extends Comparable<E>> implements List<E> {

    private static class Node<E> {

        public E value;            // reference to the value stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given value and next node.
         *
         * @param value the value to be stored
         * @param next reference to a node that should follow the new node
         */
        //constructor
        public Node(E/* int or float */ value, Node<E> next) {
            // TODO
            this.value = value;
            this.next = null;
        }

        // Accessor methods

        /**
         * Returns the value stored at the node.
         *
         * @return the value stored at the node
         */
        public E getvalue() {
            return value;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            // TODO
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param nextNode the node that should follow this one
         */
        public void setNext(Node<E> nextNode) {
            this.next = nextNode;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0 || head == null) return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {

        Node<E> temp = head;
        while (temp != null)
        {
            if (temp.equals(o))
            {
                return true;
            }
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
        return temp.value;
    }

    @Override
    public E set(int index, E value) {
        if (index < 0 || index > size || head == null)
        {
            throw new IllegalArgumentException("Invalid index size");
        }
        Node<E> temp = head;
        for(int i = 0; i < index; i++)
        {
            temp = temp.next;
        }
        temp.value = value;
        return value;
    }

    @Override
    public void add(int position, E value) {
        Node<E> newNode = new Node<E>(value, null);
        Node<E> temp = head;
        if (position == 0)
        {
            addFirst(value);
        }
        else if (position == size - 1) this.addLast(value);
        else {
            for(int i = 0; i < position - 1; i++)
            {
                if (temp.next == null)
                {
                    break;
                }
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
        size++;
    }


    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, null);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        Node<E> newNode = new Node<>(e, null);
        Node<E>temp = head;
        if (head == null)
        {
            head = newNode;
        }
        else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }


    @Override
    public E remove(int position) {
        if (head == null) return null;
        if (position < 0 || position >= size)
        {
            throw new IllegalArgumentException("This position can't be reached");
        }
        Node<E> temp = head;
        E removedValue = null;

        if (position == 0)
        {
            removedValue = head.value;
            head = head.next;
        }
        else
        {
            for(int i = 0; i < position - 1; i++)
            {
                temp = temp.next;
            }
            removedValue = temp.next.value;
            temp.next = temp.next.next;
        }
        size--;
        return removedValue;
    }

    @Override
    public int indexOf(Object o) {
        if (head == null) return -1;
        else {
            int index = 0;
            Node<E> temp = head;
            while (temp != null) {
                if (temp.equals(o)) return index;
                temp = temp.next;
                index++;
            }
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

    @Override
    public E removeFirst() {
        if (head == null) return null;
        Node<E> temp = head;
        head = head.next;
        size--;
        return temp.getvalue();
    }
    public void reverse() {
        if (head == null || head.next == null) {
            return; // Empty list or single node - already reversed
        }

        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev; // Update head to the new first node
    }


    @Override
    public E removeLast() {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            E removedValue = head.value;
            head = null;
            size--;
            return removedValue;
        }

        Node<E> temp = head;
        Node<E> prevPointer = head;

        while (temp.next != null) {
            prevPointer = temp;
            temp = temp.next;
        }

        E removedValue = temp.value;
        prevPointer.next = null;
        size--;
        return removedValue;
    }

    private Node<E> copyNodes(Node<E> originalNode) {
        if (originalNode == null) {
            return null;
        }

        Node<E> newNode = new Node<>(originalNode.value, null);

        newNode.next = copyNodes(originalNode.next);

        return newNode;
    }

    @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
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



    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            if (curr == null)
            {
                System.out.println("There is no node to traverse to.");
                return false;
            }
                return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getvalue();
            curr = curr.next;
            return res;
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getvalue());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        SinglyLinkedList<Integer> ll2 = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());

        ll2.addFirst(0);
        ll2.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        ll.remove(2);
        System.out.println(ll.remove(2));
        ll.removeFirst();
        System.out.println("I accept your apology");
        ll.add(2, 5);
        ll.add(2, 5);
        ll.add(2, 5);
        ll.add(2, 5);
        ll.add(2, 5);
        System.out.println("Nodes: ");
        System.out.println(ll);
        ll.remove(5);
        ll.copyNodes(ll2.head);
        ll.reverse();
        System.out.println(ll);

    }
}
