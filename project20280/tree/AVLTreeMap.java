package project20280.tree;

import project20280.interfaces.Entry;
import project20280.interfaces.Position;

import java.util.Comparator;

public class AVLTreeMap<K, V> extends TreeMap<K, V> {

    public AVLTreeMap() {
        super();
    }

    public AVLTreeMap(Comparator<K> comp) {
        super(comp);
    }

    // returns how tall the node is (leaves are height 0)
    protected int height(Position<Entry<K, V>> p) {
        if (p == null || isExternal(p)) {
            return 0;
        }
        return tree.getAux(p);
    }

    // recalculates height based on children
    protected void recomputeHeight(Position<Entry<K, V>> p) {
        if (p != null && isInternal(p)) {
            int h = 1 + Math.max(height(left(p)), height(right(p)));
            tree.setAux(p, h);
        }
    }

    // checks if the node is balanced - heights differ by at most 1
    protected boolean isBalanced(Position<Entry<K, V>> p) {
        if (p == null || isExternal(p)) {
            return true;
        }
        int diff = height(left(p)) - height(right(p));
        return Math.abs(diff) <= 1;
    }

    // returns the taller child (used to figure out which way to rotate)
    protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
        int leftH = height(left(p));
        int rightH = height(right(p));

        if (leftH > rightH) {
            return left(p);
        } else if (rightH > leftH) {
            return right(p);
        } else {
            // if they're equal, go with the left child
            // (doesn't really matter which one)
            return left(p);
        }
    }

    // walks up from p and fixes any unbalanced nodes it finds
    protected void rebalance(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> current = p;

        while (current != null) {
            // update height before checking balance
            recomputeHeight(current);

            if (!isBalanced(current)) {
                // need to fix this node
                current = restructure(tallerChild(tallerChild(current)));
                recomputeHeight(left(current));
                recomputeHeight(right(current));
                recomputeHeight(current);
            }

            // move up to parent
            current = parent(current);
        }
    }

    // called after inserting something - rebalance along the path up
    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        rebalance(p);
    }

    // called after deleting something - rebalance along the path up
    @Override
    protected void rebalanceDelete(Position<Entry<K, V>> p) {
        // start rebalancing from the parent since the node itself is gone
        rebalance(parent(p));
    }

    // just for debugging - checks if the tree is a valid AVL tree
    private boolean sanityCheck() {
        for (Position<Entry<K, V>> p : tree.positions()) {
            if (isInternal(p)) {
                if (p.getElement() == null)
                    System.out.println("VIOLATION: Internal node has null entry");
                else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
                    System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
                    dump();
                    return false;
                }
            }
        }
        return true;
    }

    // makes a nice string representation of the tree
    public String toBinaryTreeString() {
        BinaryTreePrinter<Entry<K, V>> btp = new BinaryTreePrinter<>(this.tree);
        return btp.print();
    }

    // test it out
    public static void main(String[] args) {
        AVLTreeMap avl = new AVLTreeMap<>();

        Integer[] arr = new Integer[]{5, 3, 10, 2, 4, 7, 11, 1, 6, 9, 12, 8};

        for (Integer i : arr) {
            if (i != null) avl.put(i, i);
            System.out.println("root " + avl.root());
        }
        System.out.println(avl.toBinaryTreeString());

        avl.remove(5);
        System.out.println(avl.toBinaryTreeString());
    }
}