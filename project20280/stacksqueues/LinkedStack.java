package project20280.stacksqueues;

import org.w3c.dom.Node;
import project20280.interfaces.Stack;
import project20280.list.DoublyLinkedList;
import project20280.tree.LinkedBinaryTree;



public class LinkedStack<E> implements Stack<E> {

    DoublyLinkedList<E> ll ;

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        convertToBinary(23);
    }

    //constructor for stack, what do we do here
    //so we call this constructor to create a linkedlist object that we can then call objects on
    public LinkedStack() {
        //initialised our linked list
        ll  = new DoublyLinkedList<>();
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void push(E e) {
        ll.addFirst(e);
    }

    @Override
    public E top() {
       return ll.get(0);
    }

    @Override
    public E pop() {
        return ll.removeFirst();
    }

    static String convertToBinary(long dec_num) {
        LinkedStack<Long> stack = new LinkedStack<>();
        StringBuilder binary = new StringBuilder();

        while ( dec_num > 0)
        {
            stack.push(dec_num % 2);
            dec_num = dec_num / 2;
        }
        while (!stack.isEmpty())
        {
           binary.append(stack.pop());
        }
        return binary.toString();
    }

    public String toString() {
        return ll.toString();
    }
}

