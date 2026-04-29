package project20280.priorityqueue;

/*
 */

import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the respective key-value pairs. The
     * two arrays given will be paired element-by-element. They are presumed to have
     * the same length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        for (int i = 0; i < keys.length; i++) {
            heap.add(new PQEntry<>(keys[i], values[i]));
        }
        heapify();
    }

    // protected utilities
    protected int parent(int j) {
        return (j - 1) / 2;
    }

    protected int left(int j) {
        return 2 * j + 1;
    }

    protected int right(int j) {
        return 2 * j + 2;
    }

    protected boolean hasLeft(int j) {
        int leftIndex = left(j);
        return leftIndex < heap.size();
    }

    protected boolean hasRight(int j) {
        int rightIndex = right(j);
        return rightIndex < heap.size();
    }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(j);
        heap.set(j, heap.get(i));
        heap.set(i, temp);
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap
     * property.
     */
    protected void upheap(int j) {
        while (j > 0) {
            int p = parent(j);
            if (compare(heap.get(p), heap.get(j)) <= 0) {
                break;
            }
            swap(j, p);
            j = p;
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    protected void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;

            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(rightIndex), heap.get(leftIndex)) < 0) {
                    smallChildIndex = rightIndex;
                }
            }
            if (compare(heap.get(j), heap.get(smallChildIndex)) <= 0) {
                break;
            }
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify() {
        int startIndex = parent(size() - 1);
        for (int j = startIndex; j >= 0; j--) {
            downheap(j);
        }
    }

    // public methods

    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        Entry<K, V> entry = new PQEntry<>(key, value);
        heap.add(entry);
        upheap(heap.size() - 1);
        return entry;
    }

    //Question 7
    public static Integer[] pqSort(Integer[] arr) {
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            pq.insert(arr[i], arr[i]);
        }

        Integer[] result = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = pq.removeMin().getKey();
        }

        return result;
    }

    //Question 6
    public static void heapSortInPlace(Integer[] arr) {
        int n = arr.length;

        // Build max-heap
        for (int i = (n - 2) / 2; i >= 0; i--) {
            downHeapStatic(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            Integer temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            downHeapStatic(arr, i, 0);
        }
    }

    private static void downHeapStatic(Integer[] arr, int size, int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && arr[left] > arr[largest]) {
                largest = left;
            }
            if (right < size && arr[right] > arr[largest]) {
                largest = right;
            }

            if (largest == i) break;

            Integer temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            i = largest;
        }
    }

    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
        if (heap.isEmpty()) {
            return null;
        }
        Entry<K, V> min = heap.get(0);
        if (heap.size() == 1) {
            return heap.remove(0);
        }
        Entry<K, V> last = heap.remove(heap.size() - 1);
        heap.set(0, last);
        downheap(0);

        return min;
    }

    public String toString() {
        return heap.toString();
    }

    /**
     * Used for debugging purposes only
     */
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            Entry<K, V> e_left, e_right;
            e_left = left < heap.size() ? heap.get(left) : null;
            e_right = right < heap.size() ? heap.get(right) : null;
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
                System.out.println("Invalid left child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
                System.out.println("Invalid right child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
        }
    }

    public static void main(String[] args) {

        //Question 2
        /*
            2 4 5 18 26 10 15 16 23 39
           */

        //Question 3
        /*
            18 26 5 15 10 4 23 39 16 2
         */
        //Question 4
        /*
            Postorder: 5, 4, 3, 6, 2, 1
            Preorder: 10, 7, 6, 5, 9, 8
         */
        Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);

        System.out.println("elements: " + java.util.Arrays.toString(rands));
        System.out.println("after adding elements: " + pq);

        System.out.println("min element: " + pq.min());

        pq.removeMin();
        System.out.println("after removeMin: " + pq);
    }
}